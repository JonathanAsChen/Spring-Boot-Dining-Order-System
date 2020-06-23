package com.as.jonathan.sell.utils;

import com.as.jonathan.sell.enums.CodeEnum;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020 11:10 PM
 */
public class EnumUtil {
	public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> enumClass) {
		for (T each : enumClass.getEnumConstants()) {
			if (code.equals(each.getCode())) {
				return each;
			}
		}
		return null;

	}

}
