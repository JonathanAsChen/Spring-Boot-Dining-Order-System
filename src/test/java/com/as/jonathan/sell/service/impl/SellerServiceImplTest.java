package com.as.jonathan.sell.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:14 AM
 */
@SpringBootTest
class SellerServiceImplTest {

	@Autowired
	SellerServiceImpl sellerService;

	@Test
	void findSellerInfoByOpenid() {
		assertNull(sellerService.findSellerInfoByOpenid("not existed"));
		assertNotNull(sellerService.findSellerInfoByOpenid("abc"));
	}
}