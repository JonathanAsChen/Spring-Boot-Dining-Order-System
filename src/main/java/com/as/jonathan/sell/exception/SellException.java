package com.as.jonathan.sell.exception;

import com.as.jonathan.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Getter
public class SellException extends  RuntimeException{

	private Integer code;

	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();

	}

	public SellException(Integer code, String message) {
		super(message);
		this.code = code;
	}
}
