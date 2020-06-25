package com.as.jonathan.sell.service;

import com.as.jonathan.sell.dataObject.SellerInfo;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:12 AM
 */
public interface SellerService {

	SellerInfo findSellerInfoByOpenid(String openid);
}
