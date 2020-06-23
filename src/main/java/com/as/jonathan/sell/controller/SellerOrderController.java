package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020 10:34 PM
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
							 @RequestParam(value = "size", defaultValue = "10") Integer size,
							 Model model) {
		PageRequest request = PageRequest.of(page - 1, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(request);
		model.addAttribute("orderDTOPage", orderDTOPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		return new ModelAndView("order/list");
	}

	@GetMapping("/cancel")
	public ModelAndView cancel(@RequestParam("orderId") String orderId,
							   Model model) {
		OrderDTO orderDTO;
		try {
			orderDTO = orderService.findOne(orderId);
			orderService.cancel(orderDTO);
		} catch (Exception e) {
			log.error("[Seller cancel order] exception {}", e);
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/sell/seller/order/list");
			return new ModelAndView("/common/error");
		}
		model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
		model.addAttribute("url", "/sell/seller/order/list");
		return new ModelAndView("/common/success");

	}

	@GetMapping("/detail/{orderId}")
	public ModelAndView detail(@PathVariable("orderId") String orderId,
							   Model model) {

		OrderDTO orderDTO;
		try {
			orderDTO = orderService.findOne(orderId);
			model.addAttribute("orderDTO", orderDTO);
		} catch (Exception e) {
			log.error("[Seller cancel order] exception {}", e);
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/sell/seller/order/list");
			return new ModelAndView("/common/error");
		}

		return new ModelAndView("/order/detail");

	}

	@GetMapping("/finish")
	public ModelAndView finish(@RequestParam("orderId") String orderId,
							   Model model) {
		OrderDTO orderDTO;
		try {
			orderDTO = orderService.findOne(orderId);
			orderService.finish(orderDTO);
		} catch (Exception e) {
			log.error("[Seller finish order] exception {}", e);
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/sell/seller/order/list");
			return new ModelAndView("/common/error");
		}
		model.addAttribute("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
		model.addAttribute("url", "/sell/seller/order/list");
		return new ModelAndView("/common/success");
	}


}
