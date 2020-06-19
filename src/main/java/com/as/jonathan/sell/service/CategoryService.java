package com.as.jonathan.sell.service;

import com.as.jonathan.sell.dataObject.ProductCategory;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
public interface CategoryService {

	ProductCategory findOne(Integer categoryId);

	List<ProductCategory> findAll();

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	ProductCategory save(ProductCategory productCategory);
}
