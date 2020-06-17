package com.as.jonathan.sell.utils;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.dataObject.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
public class OrderDTO2OrderMasterConverter {
	public static OrderMaster convert(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		return orderMaster;
	}

	public static List<OrderMaster> convert(List<OrderDTO> orderDTOList) {
		return orderDTOList.stream().map(e ->
				convert(e)
		).collect(Collectors.toList());
	}
}
