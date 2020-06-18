package com.as.jonathan.sell.service;

import com.as.jonathan.sell.DTO.OrderDTO;

/**
 * Buyer service.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/17/2020
 */
public interface BuyerService {

	//find order
	OrderDTO findOrder(String openid, String orderId);


	//cancel order
	void cancelOrder(String oepnid, String orderId);
}
