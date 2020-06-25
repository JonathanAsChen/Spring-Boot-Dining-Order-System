package com.as.jonathan.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:23 AM
 */
@Component
public class WechatOpenConfig {
	@Autowired
	private WechatAccountConfig accountConfig;

	@Bean
	public WxMpService wxOpenService() {
		WxMpService wxOpenService = new WxMpServiceImpl();
		wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
		return wxOpenService;
	}


	public WxMpConfigStorage wxOpenConfigStorage() {
		WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
		wxMpDefaultConfig.setAppId(accountConfig.getOpenAppId());
		wxMpDefaultConfig.setSecret(accountConfig.getOpenAppSecret());
		return wxMpDefaultConfig;
	}
}
