package com.as.jonathan.sell.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

	@Id
	private String productId;

	private String productName;

	private BigDecimal productPrice;

	private Integer productStock;

	private String productDescription;

	private String productIcon;

	/* product status, 0 : normal, 1: sold out */
	private Integer productStatus;

	private Integer categoryType;
}
