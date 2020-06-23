package com.as.jonathan.sell.service;

import com.as.jonathan.sell.DTO.CartDTO;
import com.as.jonathan.sell.dataObject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
public interface ProductService {

	ProductInfo findOne(String productId);

	/**
	 * find all product on sold
	 * @return
	 */
	List<ProductInfo> findUpAll();

	Page<ProductInfo> findAll(Pageable pageable);

	ProductInfo save(ProductInfo productInfo);

	void increaseStock(List<CartDTO> cartDTOList);

	void decreaseStock(List<CartDTO> cartDTOList);

	ProductInfo shelve(String productId);

	ProductInfo unshelve(String productId);

}
