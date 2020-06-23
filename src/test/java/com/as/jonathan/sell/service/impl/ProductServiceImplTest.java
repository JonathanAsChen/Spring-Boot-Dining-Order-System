package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.enums.ProductStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@SpringBootTest
class ProductServiceImplTest {


	@Autowired
	private ProductServiceImpl productService;

	@Test
	void findOne() {
		assertEquals("1", productService.findOne("1").getProductId());
	}

	@Test
	void findUpAll() {
		List<ProductInfo> productInfoList = productService.findUpAll();
		assertNotEquals(0, productInfoList.size());
	}

	@Test
	void findAll() {
		PageRequest request = PageRequest.of(1, 2);
		Page<ProductInfo> productInfoPage = productService.findAll(request);
//		System.out.println("number of element : " + productInfoPage.getTotalElements());
		assertNotEquals(0, productInfoPage.getTotalElements());
	}

	@Test
	@Transactional
	void save() {
		ProductInfo productInfo = productService.findOne("1");
		productInfo.setProductId("111");
		assertNotNull(productService.save(productInfo));

	}

	@Test
	void shelve() {
		ProductInfo productInfo = productService.shelve("1");
		assertEquals(ProductStatusEnum.UP.getCode(), productInfo.getProductStatus());
	}

	@Test
	void unshelve() {
		ProductInfo productInfo = productService.unshelve("1");
		assertEquals(ProductStatusEnum.DOWN.getCode(), productInfo.getProductStatus());
	}

}