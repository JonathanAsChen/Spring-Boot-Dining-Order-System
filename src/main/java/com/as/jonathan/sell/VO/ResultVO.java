package com.as.jonathan.sell.VO;

import lombok.Data;

/**
 * The outermost layer object returned by the HTTP request.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Data
public class ResultVO<T> {

	private Integer code;

	private String msg;

	private T data;
}
