package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.dataObject.SellerInfo;
import com.as.jonathan.sell.repository.SellerInfoRepository;
import com.as.jonathan.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:13 AM
 */
@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerInfoRepository repository;

	@Override
	public SellerInfo findSellerInfoByOpenid(String openid) {
		return repository.findByOpenid(openid);
	}
}
