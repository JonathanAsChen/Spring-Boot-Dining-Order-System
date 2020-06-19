package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.DTO.CartDTO;
import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.dataObject.OrderDetail;
import com.as.jonathan.sell.dataObject.OrderMaster;
import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.enums.OrderStatusEnum;
import com.as.jonathan.sell.enums.PayStatusEnum;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.repository.OrderDetailRepository;
import com.as.jonathan.sell.repository.OrderMasterRepository;
import com.as.jonathan.sell.service.OrderService;
import com.as.jonathan.sell.service.PayService;
import com.as.jonathan.sell.service.ProductService;
import com.as.jonathan.sell.utils.KeyUtil;
import com.as.jonathan.sell.utils.OrderDTO2OrderMasterConverter;
import com.as.jonathan.sell.utils.OrderMaster2OrderDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductService productService;

	@Autowired
	private PayService payService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		String orderId = KeyUtil.genUniqueKey();
		BigDecimal orderAmount = new BigDecimal(0);

		// 1. search for product (qty, price)
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
			ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}

			// 2. calculate total price for the order
			orderAmount = productInfo.getProductPrice()
					.multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);
			// 3. save to db (orderDetail)
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetail.setOrderId(orderId);
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailRepository.save(orderDetail);
		}
		// 4. save to db (orderMater)
		// TODO: may have multi-thread problem (solved with lock in redis)
		OrderMaster orderMaster = new OrderMaster();

		// sequence matters!
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
//		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmount);
		orderMasterRepository.save(orderMaster);

		// 5. update stock
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
				new CartDTO(e.getProductId(), e.getProductQuantity())
		).collect(Collectors.toList());

		productService.decreaseStock(cartDTOList);

		return orderDTO;
	}

	@Override
	public OrderDTO findone(String orderId) {
		OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (orderDetailList.isEmpty()) {
			throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyeropenid, Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyeropenid, pageable);

		List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

		return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
	}

	@Override
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) {


		// check order status
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("[Cancel Order] Order status not correct, orderId={}, orderStatus={}",
					orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		// update order status
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		OrderMaster orderMaster = OrderDTO2OrderMasterConverter.convert(orderDTO);

		OrderMaster update = orderMasterRepository.save(orderMaster);
		if (update == null) {
			log.error("[Cancel Order] Update order failed, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		// update stock
		if (orderDTO.getOrderDetailList().isEmpty()) {
			log.error("[Cancel Order] No order detail in OrderDTO = {}", orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
				.collect(Collectors.toList());

		productService.increaseStock(cartDTOList);

		// process refund if paid
		if (orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
			payService.refund(orderDTO);
		}

		return orderDTO;
	}

	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		//check order status
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("[Finish Order] Order status not correct, orderId={}, orderStatus={}",
					orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		//update order status
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = OrderDTO2OrderMasterConverter.convert(orderDTO);
		OrderMaster update = orderMasterRepository.save(orderMaster);
		if (update == null) {
			log.error("[Finish Order] Update order failed, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}


		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		// check order status
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("[Pay Order] Order status not correct, orderId={}, orderStatus={}",
					orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		// check pay status
		if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
			log.error("[Pay Order] Already paid for order {}", orderDTO.getOrderId());
			throw new SellException(ResultEnum.PAY_STATUS_ERROR);
		}

		// update pay status
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = OrderDTO2OrderMasterConverter.convert(orderDTO);
		OrderMaster update = orderMasterRepository.save(orderMaster);
		if (update == null) {
			log.error("[Pay Order] Update order failed, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		return orderDTO;
	}

}
