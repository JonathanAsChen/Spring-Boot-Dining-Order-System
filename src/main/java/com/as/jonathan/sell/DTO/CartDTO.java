package com.as.jonathan.sell.DTO;

import lombok.Data;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Data
public class CartDTO {
	private String productId;

	private Integer productQuantity;

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
