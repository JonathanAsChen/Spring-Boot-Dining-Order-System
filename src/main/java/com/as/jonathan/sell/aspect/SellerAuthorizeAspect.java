package com.as.jonathan.sell.aspect;

import com.as.jonathan.sell.exception.SellerAuthorizeExcption;
import com.as.jonathan.sell.utils.CookieUtil;
import constant.CookieConstant;
import constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/24/2020 8:24 PM
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
	@Autowired
	StringRedisTemplate redisTemplate;

	@Pointcut("execution(public * com.as.jonathan.sell.controller.Seller*.*(..))" +
	"&& !execution(public * com.as.jonathan.sell.controller.SellerUserController.*(..))")
	public void authenticate() {

	}

	@Before("authenticate()")
	public void doAuthenticate() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie == null) {
			//not logged in
			log.warn("[Authentication] cannot find token from cookie");
			throw new SellerAuthorizeExcption();
		}
		//find token in redis
		String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TKOEN_PREFIX, cookie.getValue()));
		if (StringUtils.isEmpty(tokenValue)) {
			log.warn("[Authentication] cannot find token from redis");
			throw new SellerAuthorizeExcption();
		}

	}
}
