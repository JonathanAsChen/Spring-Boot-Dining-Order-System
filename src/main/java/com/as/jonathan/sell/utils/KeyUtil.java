package com.as.jonathan.sell.utils;

import java.util.Random;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
public class KeyUtil {
	/**
	 * generate unique primary key.
	 * format : time + random value
	 * @return
	 */
	public static synchronized String genUniqueKey() {
		Random random = new Random();


		Integer a = random.nextInt(900000) + 100000;

		return System.currentTimeMillis() + String.valueOf(a);
	}
}
