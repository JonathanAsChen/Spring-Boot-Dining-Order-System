package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.dataObject.SellerInfo;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.service.SellerService;
import com.as.jonathan.sell.utils.CookieUtil;
import constant.CookieConstant;
import constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:54 AM
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@GetMapping("/login")
	public ModelAndView login(@RequestParam("openid") String openid,
							  HttpServletResponse response,
							  Model model) {
		// check with openid in db
		SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
		if (sellerInfo == null) {
			model.addAttribute("msg", ResultEnum.LOGIN_FAIL.getMsg());
			model.addAttribute("url", "/sell/seller/order/list");
			return new ModelAndView("common/error");
		}

		//set token to redis
		String token = UUID.randomUUID().toString();

		redisTemplate.opsForValue().set(String.format(RedisConstant.TKOEN_PREFIX, token), openid, RedisConstant.EXPIRE, TimeUnit.SECONDS);

		//set token to cookie
		CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.MAX_AGE);

		return new ModelAndView("redirect:/seller/order/list");
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		//search from cookie
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie != null) {
			//clear redis record
			redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TKOEN_PREFIX, cookie.getValue()));
			//clear cookie record
			CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
		}
		model.addAttribute("msg", ResultEnum.LOGOUT.getMsg());
		model.addAttribute("url", "/sell/seller/order/list");

		return new ModelAndView("common/success");
	}

//	@GetMapping("/loginPage")


}
