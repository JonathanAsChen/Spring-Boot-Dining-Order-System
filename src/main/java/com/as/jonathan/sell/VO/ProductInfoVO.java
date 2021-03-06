package com.as.jonathan.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Detailed info view object for each product.
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Data
public class ProductInfoVO {

	@JsonProperty("id")
	private String productId;

	@JsonProperty("name")
	private String productName;

	@JsonProperty("price")
	private BigDecimal productPrice;

	@JsonProperty("description")
	private String productDescription;

	@JsonProperty("icon")
	private String productIcon;
}
