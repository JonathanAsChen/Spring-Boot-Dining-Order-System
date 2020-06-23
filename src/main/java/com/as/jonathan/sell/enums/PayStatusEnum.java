package com.as.jonathan.sell.enums;

import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Getter
public enum PayStatusEnum implements CodeEnum<Integer> {

	WAIT(0, "Not paid"),
	SUCCESS(1, "Paid"),
	;


	private Integer code;
	private String msg;

	PayStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
