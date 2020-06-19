package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.service.OrderService;
import com.as.jonathan.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	private PayService payService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/create")
	public ModelAndView create(@RequestParam("orderId") String orderId,
							   @RequestParam("returnUrl") String returnUrl,
							   Map<String, Object> map) {

		//find order
		OrderDTO orderDTO = orderService.findone(orderId);

		//create pay
		PayResponse payResponse = payService.create(orderDTO);
		map.put("payResponse", payResponse);
		map.put("returnUrl", returnUrl);

		return new ModelAndView("pay/create", map);
	}

	@PostMapping("/notify")
	public ModelAndView notify(@RequestBody String notifyData) {
		payService.notify(notifyData);

		//Return result of payment handling back to weChat.
		return new ModelAndView("pay/success");
	}
}