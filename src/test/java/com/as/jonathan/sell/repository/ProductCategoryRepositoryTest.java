package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@SpringBootTest
class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository repository;

	@Test
	public void findOneTest() {
		ProductCategory productCategory = repository.findById(1).orElse(null);
		System.out.println(productCategory);
	}

	@Test
	@Transactional
	public void saveTest() {
		ProductCategory productCategory = new ProductCategory("test", 3);

		ProductCategory result = repository.save(productCategory);
		assertNotNull(result);

	}

	@Test
	@Transactional
	public void modifyTest() {
		ProductCategory productCategory = repository.findById(1).orElse(null);
		productCategory.setCategoryId(1);
		productCategory.setCategoryName("hot - modified3");
		productCategory.setCategoryType(15);
		repository.save(productCategory);
	}

	@Test
	public void findByCategoryTypeInTest() {
		List<Integer> list = Arrays.asList(3, 4, 15);

		List<ProductCategory> result = repository.findByCategoryTypeIn(list);
		assertNotEquals(0, result.size());
	}
}