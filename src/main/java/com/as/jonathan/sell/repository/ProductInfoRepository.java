package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

	List<ProductInfo> findByProductStatus(Integer productStatus);
}
