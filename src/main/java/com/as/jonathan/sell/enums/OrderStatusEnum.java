package com.as.jonathan.sell.enums;

import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Getter
public enum OrderStatusEnum {
	NEW(0, "new order"),
	FINISHED(1, "complete"),
	CANCEL(2, "canceled"),
	;

	private Integer code;
	private String msg;

	OrderStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
