package com.as.jonathan.sell.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Order form object transferred from json form sent by front end.
 *
 * Problem: What if field name and json field name mismatch? (easy to copy properties using BeanUtils)
 * Solved: On top of JsonProperty annotation, setter method name should be consistent with json field name.
 *
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Data
public class OrderForm {


	/**
	 * Buyer name
	 */
	@NotEmpty(message = "Name required")
	@JsonProperty("name")
	private String buyerName;

	/**
	 * Buyer phone
	 */
	@NotEmpty(message = "Phone number requried")
	@JsonProperty("phone")
	private  String buyerPhone;

	/**
	 * Buyer address
	 */
	@NotEmpty(message = "Address required")
	@JsonProperty("address")
	private String buyerAddress;

	/**
	 * Buyer openid
	 */
	@NotEmpty(message = "Openid required")
	@JsonProperty("openid")
	private String buyerOpenid;

	/**
	 * Items in cart
	 */
	@NotEmpty(message = "Cart cannot be empty")
	private String items;

	@JsonProperty("name")
	public void setName(String buyerName) {
		this.buyerName = buyerName;
	}

	@JsonProperty("phone")
	public void setPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	@JsonProperty("address")
	public void setAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	@JsonProperty("openid")
	public void setOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
	}

	public void setItems(String items) {
		this.items = items;
	}
}
