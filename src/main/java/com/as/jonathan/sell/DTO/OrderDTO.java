package com.as.jonathan.sell.DTO;

import com.as.jonathan.sell.dataObject.OrderDetail;
import com.as.jonathan.sell.enums.OrderStatusEnum;
import com.as.jonathan.sell.enums.PayStatusEnum;
import com.as.jonathan.sell.utils.serializer.Data2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
//@JsonInclude(JsonInclude.Include.NON_NULL) // will not return null field
//Implemented globally by modify configuration
public class OrderDTO {

	private String orderId;

	private String buyerName;

	private String buyerPhone;

	private String buyerAddress;

	private String buyerOpenid;

	private BigDecimal orderAmount;

	private Integer orderStatus = OrderStatusEnum.NEW.getCode();


	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	@JsonSerialize(using = Data2LongSerializer.class)
	private Date createTime;

	@JsonSerialize(using = Data2LongSerializer.class)
	private Date updateTime;

	private List<OrderDetail> orderDetailList;
}
