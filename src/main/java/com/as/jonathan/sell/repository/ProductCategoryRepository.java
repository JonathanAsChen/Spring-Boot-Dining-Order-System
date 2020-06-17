package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {


	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
