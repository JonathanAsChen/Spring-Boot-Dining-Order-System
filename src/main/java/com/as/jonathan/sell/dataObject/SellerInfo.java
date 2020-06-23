package com.as.jonathan.sell.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:04 AM
 */
@Entity
@Data
public class SellerInfo {

	@Id
	private String sellerId;

	private String username;

	private String password;

	private String openid;
}
