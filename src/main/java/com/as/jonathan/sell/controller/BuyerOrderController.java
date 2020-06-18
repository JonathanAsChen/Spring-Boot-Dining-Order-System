package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.DTO.OrderDTO;
import com.as.jonathan.sell.VO.ResultVO;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.form.OrderForm;
import com.as.jonathan.sell.service.OrderService;
import com.as.jonathan.sell.utils.OrderForm2OrderDTOConverter;
import com.as.jonathan.sell.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Create order.
	 * @param orderForm
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
												BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("[Create Order] params not correct, orderFrom = {}", orderForm);

			//TODO: exceptionHandler? AOP? Return exception message to front end?
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}

		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if (orderDTO.getOrderDetailList().isEmpty()) {
			//TODO: transfer this exception to sevice layer
			log.error("[Create Order] Cart cannot be empty");
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
			}

		OrderDTO created = orderService.create(orderDTO);
		Map<String, String> map = new HashMap<>();
		map.put("orderId", created.getOrderId());

		return ResultVOUtils.success(map);

	}


	//list order
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
										 @RequestParam(value = "page", defaultValue = "0") Integer page,
										 @RequestParam(value = "size", defaultValue = "10") Integer size) {

		if (openid.isEmpty()) {
			log.error("[List Order] openid is empty");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}

		PageRequest request = PageRequest.of(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);



		return ResultVOUtils.success(orderDTOPage.getContent());
	}

	/**
	 * order detail.
	 * @param openid
	 * @param orderId
	 * @return
	 */
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
									 @RequestParam("orderId") String orderId) {
		//TODO: authorization
		OrderDTO orderDTO = orderService.findone(orderId);
		return ResultVOUtils.success(orderDTO);
	}

	//cancel detail
	@PostMapping("/cancel")
	public ResultVO cancel(@RequestParam("openid") String openid,
						   @RequestParam("orderId") String orderId) {
		//TODO: authorization
		orderService.cancel(orderService.findone(orderId));

		return ResultVOUtils.success();
	}
}
