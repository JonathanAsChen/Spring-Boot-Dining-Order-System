package com.as.jonathan.sell.utils;

import com.as.jonathan.sell.VO.ResultVO;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
public class ResultVOUtils {

	public static ResultVO success(Object object) {
		ResultVO resultVO = new ResultVO();
		resultVO.setData(object);
		resultVO.setMessage("success");
		resultVO.setCode(0);

		return resultVO;
	}

	public static ResultVO success() {
		return success(null);
	}

	public static ResultVO error(Integer code, String msg) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(code);
		resultVO.setMessage(msg);
		return resultVO;
	}
}
