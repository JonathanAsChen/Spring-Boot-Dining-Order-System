package com.as.jonathan.sell.repository;

import com.as.jonathan.sell.dataObject.SellerInfo;
import com.as.jonathan.sell.utils.KeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:07 AM
 */
@SpringBootTest
class SellerInfoRepositoryTest {

	@Autowired
	private SellerInfoRepository repository;

	@Test
	public void save() {
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(KeyUtil.genUniqueKey());
		sellerInfo.setUsername("admin");
		sellerInfo.setPassword("admin");
		sellerInfo.setOpenid("abc");
		assertNotNull(repository.save(sellerInfo));
	}

	@Test
	void findByOpenid() {
		assertNotNull(repository.findByOpenid("abc"));
		assertNull(repository.findByOpenid("openid which is not existed"));

	}
}