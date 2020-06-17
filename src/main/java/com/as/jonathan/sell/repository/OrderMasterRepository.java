package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/16/2020
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);


}
