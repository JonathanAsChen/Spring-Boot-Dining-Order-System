package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
@SpringBootTest
class OrderDetailRepositoryTest {

	@Autowired
	private OrderDetailRepository repository;

	@Test
	void save() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("1");
		orderDetail.setOrderId("1");
		orderDetail.setProductId("1");
		orderDetail.setProductIcon("http://test.jpg");
		orderDetail.setProductName("test porridge");
		orderDetail.setProductPrice(new BigDecimal(3.2));
		orderDetail.setProductQuantity(4);
		assertNotNull(repository.save(orderDetail));

	}

	@Test
	void findByOrderId() {
		assertNotEquals(0, repository.findByOrderId("1").size());
		assertEquals(0, repository.findByOrderId("123123").size());

	}
}