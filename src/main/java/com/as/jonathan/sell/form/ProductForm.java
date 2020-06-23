package com.as.jonathan.sell.form;

import com.as.jonathan.sell.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/22/2020 9:02 PM
 */
@Data
public class ProductForm {

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
}
