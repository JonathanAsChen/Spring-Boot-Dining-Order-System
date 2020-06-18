package com.as.jonathan.sell.utils;


import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.dataObject.OrderDetail;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

	public static OrderDTO convert(OrderForm orderForm) {
		Gson gson = new Gson();

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderForm, orderDTO);

		List<OrderDetail> orderDetailList;
		// convert json to list
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(),
					new TypeToken<List<OrderDetail>>(){}.getType());
		} catch (JsonSyntaxException e) {
			log.error("[Convert Json] Error, String = {}", orderForm.getItems());
			throw new SellException(ResultEnum.PARAM_ERROR);
		}


		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}
}
