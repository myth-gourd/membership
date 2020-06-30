package com.git.myth.gourd.membership.server.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "membership")
public class MembershipConfiguration {

	private static final boolean DEFAULT_ENABLE_SWAGGER_UI = false;
	
	private boolean enableSwaggerUI = DEFAULT_ENABLE_SWAGGER_UI;

	public boolean isEnableSwaggerUI() {
		return enableSwaggerUI;
	}

	public void setEnableSwaggerUI(boolean enableSwaggerUI) {
		this.enableSwaggerUI = enableSwaggerUI;
	}
}