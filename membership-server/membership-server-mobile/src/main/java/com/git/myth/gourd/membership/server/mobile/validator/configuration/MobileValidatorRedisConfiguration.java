package com.git.myth.gourd.membership.server.mobile.validator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.git.myth.gourd.membership.server.util.RedisConfiguration;

@Configuration
@ConfigurationProperties(prefix = "membership.mobile.validator.redis")
public class MobileValidatorRedisConfiguration extends RedisConfiguration {
}