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
public class OrderMaster2OrderDTOConverter {

	public static OrderDTO convert(OrderMaster orderMaster) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}

	public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
		return orderMasterList.stream().map(e ->
				convert(e)
				).collect(Collectors.toList());
	}

}
