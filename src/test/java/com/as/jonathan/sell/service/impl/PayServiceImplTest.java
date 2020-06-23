package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.service.OrderService;
import com.as.jonathan.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
@SpringBootTest
@Slf4j
class PayServiceImplTest {

	@Autowired
	private PayService payService;

	@Autowired
	private OrderService orderService;

	@Test
	void create() {
		OrderDTO orderDTO = orderService.findOne("1592368958190915162");
		payService.create(orderDTO);
	}

	@Test
	void refund() {
		OrderDTO orderDTO = orderService.findOne("1592368958190915162");
		payService.refund(orderDTO);
	}
}