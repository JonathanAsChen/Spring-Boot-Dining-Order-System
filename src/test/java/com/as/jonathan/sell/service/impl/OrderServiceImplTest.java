package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.dataObject.OrderDetail;
import com.as.jonathan.sell.enums.OrderStatusEnum;
import com.as.jonathan.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@SpringBootTest
@Slf4j
class OrderServiceImplTest {

	@Autowired
	private  OrderServiceImpl orderService;

	private final String ORDER_ID = "1592355886561859732"; // some test result from database...

	private final String BUYER_OPENID = "test openid";
	@Test
	void create() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerAddress("test addr");
		orderDTO.setBuyerName("test name!");
		orderDTO.setBuyerOpenid(BUYER_OPENID);
		orderDTO.setBuyerPhone("test phone!");

		List<OrderDetail> orderDetailList = new ArrayList<>();
		OrderDetail od1 = new OrderDetail();
		od1.setProductId("1");
		od1.setProductQuantity(5);
		orderDetailList.add(od1);


		orderDTO.setOrderDetailList(orderDetailList);

		OrderDTO result = orderService.create(orderDTO);
		log.info("[CREATE ORDER TEST] result = {}",result);
		assertNotNull(result);
	}

	@Test
	void findone() {
		OrderDTO result = orderService.findOne(ORDER_ID);
		log.info("[find one order] result = {}", result);
		assertEquals(ORDER_ID, result.getOrderId());
	}

	@Test
	void findList() {
		PageRequest request = PageRequest.of(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
		assertNotEquals(0, orderDTOPage.getTotalElements());
	}

	@Test
	@Transactional
	void cancel() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.cancel(orderDTO);
		assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
	}

	@Test
	@Transactional
	void finish() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.finish(orderDTO);
		assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
	}

	@Test
	@Transactional
	void paid() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.paid(orderDTO);
		assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
	}

	@Test
	public void list() {
		PageRequest request = PageRequest.of(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findList(request);
		assertNotEquals(0, orderDTOPage.getTotalElements());
		assertTrue(orderDTOPage.getTotalElements() > 0, "find all the orders");

	}
}