package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.service.BuyerService;
import com.as.jonathan.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/17/2020
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private OrderService orderService;

	@Override
	public OrderDTO findOrder(String openid, String orderId) {
		return checkOrderOwner(openid, orderId);
	}

	@Override
	public void cancelOrder(String openid, String orderId) {
		OrderDTO orderDTO = checkOrderOwner(openid, orderId);
		orderService.cancel(orderDTO);
	}

	private OrderDTO checkOrderOwner(String openid, String orderId) {
		//Authorization
		OrderDTO orderDTO = orderService.findOne(orderId);
		if(!orderDTO.getBuyerOpenid().equals(openid)) {
			log.error("[Order detail] Order does not belong to the user.");
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}
}
