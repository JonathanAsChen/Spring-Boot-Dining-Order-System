package com.as.jonathan.sell.dataObject.mapper;

import com.as.jonathan.sell.dataObject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/24/2020 9:03 PM
 */
@SpringBootTest
@Slf4j
class ProductCategoryMapperTest {

	@Autowired
	private ProductCategoryMapper mapper;

	@Test
	void insertByMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("category_name", "new category");
		map.put("category_type", 111);

		int result = mapper.insertByMap(map);
		assertEquals(1, result);
	}

	@Test
	public void insertByObject() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryType(112);
		productCategory.setCategoryName("test object");
		int result = mapper.insertByObject(productCategory);
		assertEquals(1, result);
	}

	@Test
	public void findByCategoryType() {
		ProductCategory result = mapper.findBycategoryType(111);
		assertNotNull(result);
	}

	@Test
	public void findBycategoryName() {
		List<ProductCategory> result = mapper.findBycategoryName("test object");
		assertNotEquals(0, result.size());
	}

	@Test
	public void updateByCategoryType() {
		assertNotEquals(0, mapper.updateByCategoryType("edit test object", 112));

	}

	@Test
	public void updateByObject() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryType(112);
		productCategory.setCategoryName("test object");
		assertNotEquals(0, mapper.updateByObject(productCategory));

	}

	@Test
	public void deleteByCategoryType() {
		int result = mapper.deleteByCategoryType(112);
		assertEquals(1, result);
	}

	@Test
	public void xmlFindByCategoryType() {
		ProductCategory productCategory = mapper.xmlFindByCategoryType(111);
		assertNotNull(productCategory);
	}
}