package com.as.jonathan.sell.enums;


import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Getter
public enum ResultEnum {
	PARAM_ERROR(1, "params not correct"),

	PRODUCT_NOT_EXIST(10, "product not exist"),

	PRODUCT_STOCK_ERROR(11, "short in stock"),

	ORDER_NOT_EXIST(12, "order not exist"),

	ORDER_DETAIL_NOT_EXIST(13, "order detail not exist"),

	ORDER_STATUS_ERROR(14, "order status not correct, cannot cancel"),

	ORDER_UPDATE_FAIL(15, "order update failed"),

	ORDER_DETAIL_EMPTY(16, "order detail is empty"),

	PAY_STATUS_ERROR(17, "already paid"),

	ORDER_OWNER_ERROR(19, "Order does not belongs to current user"),

	WECHAT_MP_ERROR(20, "Wechat MP error"),

	WECHAT_PAY_MONEY_VERIFY_ERROR(21, "Order amount mismatched"),

	;


	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


}
