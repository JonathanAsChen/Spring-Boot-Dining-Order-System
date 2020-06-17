package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.dataObject.ProductCategory;
import com.as.jonathan.sell.repository.ProductCategoryRepository;
import com.as.jonathan.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public ProductCategory findOne(Integer categoryId) {
		return repository.findById(categoryId).orElse(null);
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return repository.save(productCategory);
	}
}
