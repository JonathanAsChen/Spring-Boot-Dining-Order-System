package com.as.jonathan.sell.service;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
public interface PayService {

	PayResponse create(OrderDTO orderDTO);

	PayResponse notify(String notifyData);

	RefundResponse refund(OrderDTO orderDTO);


}
