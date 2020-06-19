package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@SpringBootTest
class OrderMasterRepositoryTest {

	@Autowired
	private OrderMasterRepository repository;


	@Test
	void save() {
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("2");
		orderMaster.setBuyerName("test name again");
		orderMaster.setBuyerPhone("99998888");
		orderMaster.setBuyerAddress("test address");
		orderMaster.setBuyerOpenid("222");
		orderMaster.setOrderAmount(new BigDecimal(2.5));
		assertNotNull(repository.save(orderMaster));
	}


	@Test
	void findByBuyerOpenid() {
		assertNotEquals(0, repository.findByBuyerOpenid(
				"123", PageRequest.of(1, 2)).getTotalElements());
	}
}