package com.as.jonathan.sell.enums;

import lombok.Getter;

/**
 * Product status.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Getter
public enum ProductStatusEnum {
	UP(0, "on sell"),
	DOWN(0, "sold out");

	private Integer code;
	private String message;

	ProductStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
