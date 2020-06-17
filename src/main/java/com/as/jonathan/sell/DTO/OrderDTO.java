package com.as.jonathan.sell.DTO;

import com.as.jonathan.sell.dataObject.OrderDetail;
import com.as.jonathan.sell.enums.OrderStatusEnum;
import com.as.jonathan.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Data
public class OrderDTO {

	private String orderId;

	private String buyerName;

	private String buyerPhone;

	private String buyerAddress;

	private String buyerOpenid;

	private BigDecimal orderAmount;

	private Integer orderStatus = OrderStatusEnum.NEW.getCode();


	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	private Date createTime;

	private Date updateTime;

	private List<OrderDetail> orderDetailList;
}
