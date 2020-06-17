package com.as.jonathan.sell.exception;

import com.as.jonathan.sell.enums.ResultEnum;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
public class SellException extends  RuntimeException{

	private Integer code;

	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();

	}
}
