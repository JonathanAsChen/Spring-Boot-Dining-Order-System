package com.as.jonathan.sell.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

	@Id
	private String detailId;

	private String orderId;

	private String productId;

	private String productName;

	private BigDecimal productPrice;

	private Integer productQuantity;

	private String productIcon;

//	private Date createTime;
//
//	private Date updateTime;
}
