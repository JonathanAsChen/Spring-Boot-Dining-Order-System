package com.as.jonathan.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/23/2020 10:47 AM
 */
@Data
@ConfigurationProperties(prefix = "project.url")
@Component
public class ProjectUrlConfig {
	private String wechatMpAuthorize;

	private String wechatOpenAuthorize;

	private String sell;
}
