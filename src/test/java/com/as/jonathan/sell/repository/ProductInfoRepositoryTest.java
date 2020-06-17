package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@SpringBootTest
class ProductInfoRepositoryTest {

	@Autowired
	private  ProductInfoRepository repository;

	@Test
	public void save() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("1");
		productInfo.setProductName("test porridge");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(100);
		productInfo.setProductDescription("test description");
		productInfo.setProductStatus(0);
		productInfo.setProductIcon("http://test.jpg");
		productInfo.setCategoryType(2);
		assertNotNull(repository.save(productInfo));
	}



	@Test
	void findByProductStatus() throws Exception{
		List<ProductInfo> productInfoList = repository.findByProductStatus(0);
		assertNotEquals(0, productInfoList.size());
	}
}