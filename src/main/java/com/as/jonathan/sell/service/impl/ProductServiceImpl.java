package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.enums.ProductStatusEnum;
import com.as.jonathan.sell.repository.ProductInfoRepository;
import com.as.jonathan.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoRepository repository;

	@Override
	public ProductInfo findOne(String productId) {
		return repository.findById(productId).orElse(null);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}
}
