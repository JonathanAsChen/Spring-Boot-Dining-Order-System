package com.as.jonathan.sell.enums;


import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Getter
public enum ResultEnum {
	PRODUCT_NOT_EXIST(10, "product not exist"),

	PRODUCT_STOCK_ERROR(11, "short in stock"),

	ORDER_NOT_EXIST(12, "order not exist"),

	ORDER_DETAIL_NOT_EXIST(13, "order detail not exist"),

	ORDER_STATUS_ERROR(14, "order status not correct, cannot cancel"),

	ORDER_UPDATE_FAIL(15, "order update failed"),

	ORDER_DETAIL_EMPTY(16, "order detail is empty"),

	PAY_STATUS_ERROR(17, "already paid"),


	;


	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


}
