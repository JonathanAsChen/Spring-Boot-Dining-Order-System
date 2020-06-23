package com.as.jonathan.sell.enums;

import lombok.Getter;

/**
 * Product status.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
	UP(0, "on shelf"),
	DOWN(1, "off shelf");

	private Integer code;
	private String message;

	ProductStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
