package com.as.jonathan.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * View object for product at front end.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Data
public class ProductVO {

	@JsonProperty("name")
	private String categoryName;

	@JsonProperty("type")
	private Integer categoryType;

	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;
}
