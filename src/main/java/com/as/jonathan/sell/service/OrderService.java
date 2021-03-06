package com.as.jonathan.sell.service;

import com.as.jonathan.sell.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
public interface OrderService {

	/* create order*/
	OrderDTO create(OrderDTO orderDTO);

	/* find one order*/
	OrderDTO findOne(String orderId);

	/* find order page list by buyer openid*/
	Page<OrderDTO> findList(String buyeropenid, Pageable pageable);

	/* find order page list of all users*/
	Page<OrderDTO> findList(Pageable pageable);


	/* cancel order*/
	OrderDTO cancel(OrderDTO orderDTO);

	/* complete order*/
	OrderDTO finish(OrderDTO orderDTO);

	/* pay for order*/
	OrderDTO paid(OrderDTO orderDTO);
}
