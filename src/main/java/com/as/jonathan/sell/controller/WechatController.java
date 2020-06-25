package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.config.ProjectUrlConfig;
import com.as.jonathan.sell.config.WechatAccountConfig;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;
import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxMpService wxOpenService;

	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl) {
//		System.out.println(wxMpService.getWxMpConfigStorage().getAppId());
//		System.out.println(wxMpService.getWxMpConfigStorage().getSecret());

		String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
		String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, returnUrl);
		log.info("[Wechat Auth] Get code, redirectUrl = {}", redirectUrl);

		return "redirect:" + redirectUrl;
	}

	@GetMapping("/userInfo")
	public String userInfo(@RequestParam("code") String code,
						 @RequestParam("state") String returnUrl) {

		WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
		try {
			wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			log.error("[Wechat Auth] {}", e);
			throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
		}
		String openid = wxMpOAuth2AccessToken.getOpenId();

		return "redirect:" + returnUrl + "?openid=" + openid;
	}

	@GetMapping("/qrAuthorize")
	public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
		String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
		String redirectUrl;
		try {
			redirectUrl = wxOpenService.buildQrConnectUrl(
					url,
					WxConsts.QrConnectScope.SNSAPI_LOGIN,
					URLEncoder.encode(returnUrl, java.nio.charset.StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			throw new SellException(ResultEnum.ENCODE_ERROR);
		}

		return "redirect:" + redirectUrl;

	}

	@GetMapping("/qrUserInfo")
	public String qrUserInfo(@RequestParam("code") String code,
							 @RequestParam("state") String returnUrl) {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
		try {
			wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			log.error("[Wechat Seller Auth] {}", e);
			throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
		}
		String openid = wxMpOAuth2AccessToken.getOpenId();
		return "redirect:" + returnUrl + "?openid=" + openid;
	}

}
