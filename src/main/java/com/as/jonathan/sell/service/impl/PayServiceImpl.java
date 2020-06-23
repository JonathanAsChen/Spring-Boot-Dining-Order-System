package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.service.OrderService;
import com.as.jonathan.sell.service.PayService;
import com.as.jonathan.sell.utils.MathUtil;
import com.as.jonathan.sell.utils.serializer.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

	private static final String ORDER_NAME = "Wechat dining order";

	@Autowired
	private BestPayServiceImpl bestPayService;

	@Autowired
	private OrderService orderService;

	@Override
	public PayResponse create(OrderDTO orderDTO) {
		PayRequest payRequest = new PayRequest();
		payRequest.setOpenid(orderDTO.getBuyerOpenid());
		payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
		payRequest.setOrderId(orderDTO.getOrderId());
		payRequest.setOrderName(ORDER_NAME);
		payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

		PayResponse payResponse = bestPayService.pay(payRequest);
		log.info("[Wechat pay] request:{}, response:{}", JsonUtil.toJson(payRequest), JsonUtil.toJson(payResponse));
		return payResponse;
	}

	@Override
	public PayResponse notify(String notifyData) {
		// verify signature
		// check pay status returned
		PayResponse payResponse = bestPayService.asyncNotify(notifyData);
		log.info("[Wechat pay] async notification: {}",payResponse);



		OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
		// paid amount (0.10 vs 0.1 problem)
		if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
			log.error("[Wechat pay] async notification. Order amount mismatched. orderId={}, notified amount={}, system amount={}",
					payResponse.getOrderId(),
					payResponse.getOrderAmount(),
					orderDTO.getOrderAmount());
			throw new SellException(ResultEnum.WECHAT_PAY_MONEY_VERIFY_ERROR);
		}


		// payer? (payer == order placer?) no restriction here

		// update pay status
		orderService.paid(orderDTO);
		return payResponse;
	}

	@Override
	public RefundResponse refund(OrderDTO orderDTO) {
		RefundRequest refundRequest = new RefundRequest();
		refundRequest.setOrderId(orderDTO.getOrderId());
		refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
		refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
		log.info("[Wechat refund] request={}", JsonUtil.toJson(refundRequest));

		RefundResponse refundResponse = bestPayService.refund(refundRequest);
		log.info("[Wechat refund] response={}", JsonUtil.toJson(refundResponse));

		return refundResponse;
	}
}
