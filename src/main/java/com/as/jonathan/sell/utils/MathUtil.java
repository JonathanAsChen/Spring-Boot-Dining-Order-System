package com.as.jonathan.sell.utils;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
public class MathUtil {

	private static final Double MONEY_ACCURACY_THRESHOLD = 0.01;

	public static Boolean equals(Double d1, Double d2) {
		return Math.abs(d1 - d2) < MONEY_ACCURACY_THRESHOLD;
	}
}
