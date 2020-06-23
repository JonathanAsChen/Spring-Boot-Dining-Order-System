package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:05 AM
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

	SellerInfo findByOpenid(String openid);

}

