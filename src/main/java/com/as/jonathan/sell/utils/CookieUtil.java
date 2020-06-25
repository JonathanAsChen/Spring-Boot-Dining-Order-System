package com.as.jonathan.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie util.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/24/2020 7:48 PM
 */
public class CookieUtil {

	/**
	 * set cookie into response.
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void set(HttpServletResponse response,
						   String name,
						   String value,
						   Integer maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);

	}

	/**
	 * get cookie from request.
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie get(HttpServletRequest request,
							 String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			return cookieMap.get(name);
		} else {
			return null;
		}
	}

	/**
	 * put cookies into a map;
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
