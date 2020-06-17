package com.as.jonathan.sell.dataObject;

import com.as.jonathan.sell.enums.OrderStatusEnum;
import com.as.jonathan.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

	@Id
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

	//put it in DTO (data transfer object)
//	@Transient
//	private List<OrderDetail> orderDetailList;

}
