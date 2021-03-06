package com.as.jonathan.sell.dataObject;

import com.as.jonathan.sell.enums.ProductStatusEnum;
import com.as.jonathan.sell.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

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

	/* product status, 0 : on sell, 1: off shelf */
	private Integer productStatus = ProductStatusEnum.UP.getCode();

	private Integer categoryType;

	private Date createTime;
	private Date updateTime;

	@JsonIgnore
	public ProductStatusEnum getProductStatusEnum() {
		return EnumUtil.getEnumByCode(productStatus, ProductStatusEnum.class);
	}
}
