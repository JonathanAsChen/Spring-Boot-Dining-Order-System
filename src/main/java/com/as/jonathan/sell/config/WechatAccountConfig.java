package com.as.jonathan.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

	private String mpAppId;

	private String mpAppSecret;

	//商户id
	private String mchId;

	private String mchKey;

	private String keyPath;

	// wechat pay async notify url
	private String notifyUrl;


	private String openAppId;

	private String openAppSecret;
}
