package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

	List<OrderDetail> findByOrderId(String orderId);
}
