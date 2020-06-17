package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.dataObject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@SpringBootTest
class CategoryServiceImplTest {

	@Autowired
	private CategoryServiceImpl categoryService;

	@Test
	void findOne() {
		ProductCategory productCategory = categoryService.findOne(1);
		assertEquals(1, productCategory.getCategoryId());
	}

	@Test
	void findAll() {
		List<ProductCategory> result = categoryService.findAll();
		assertNotEquals(0, result.size());
	}

	@Test
	void findByCategoryTypeIn() {
		List<Integer> list = Arrays.asList(3, 15, 4);
		List<ProductCategory> result = categoryService.findByCategoryTypeIn(list);
		assertNotEquals(0, result.size());
	}

	@Test
	@Transactional
	void save() {
		ProductCategory result = categoryService.save(new ProductCategory("test name", 100));
		assertNotNull(result);
	}
}