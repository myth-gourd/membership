package com.git.myth.gourd.membership.server.mobile.validator.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorTemplate;

@Configuration
@ConfigurationProperties(prefix = "membership.mobile.validator")
public class MobileValidatorConfiguration {

	private static final int DEFAULT_EXPIRED = 300;
	
	private int expired = DEFAULT_EXPIRED;
	
	private List<MobileValidatorTemplate> templates;
	
	public int getExpired() {
		return expired;
	}

	public void setExpired(int expired) {
		this.expired = expired;
	}

	public List<MobileValidatorTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(List<MobileValidatorTemplate> templates) {
		this.templates = templates;
	}
}