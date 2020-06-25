package com.as.jonathan.sell.handler;

import com.as.jonathan.sell.exception.SellerAuthorizeExcption;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/24/2020 8:34 PM
 */
@ControllerAdvice
public class SellerExceptionHandler {



	//intercept login exception
	@ExceptionHandler(value = SellerAuthorizeExcption.class)
	public ModelAndView handlerAuthorizeException() {
		return new ModelAndView("redirect:".concat("/seller/loginPage" /*should be "/wechat/qrAuthorize...."*/));
	}
}
