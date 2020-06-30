package com.git.myth.gourd.membership.server.mobile.validator.persistance.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.git.myth.gourd.membership.server.mobile.validator.configuration.MobileValidatorRedisConfiguration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MobileValidatorRedisPool {

	private JedisPool pool;
	
	@Autowired
	private MobileValidatorRedisConfiguration configuration;
	
	@PostConstruct
	public void init(){
		
		String password = configuration.getPassword();
		if (StringUtils.isEmpty(password))
		{
			password = null;
		}
		this.pool = new JedisPool(configuration.getPool(), configuration.getHost(), configuration.getPort(), configuration.getTimeout(), password, configuration.getDatabase());
	}
	
	public Jedis getJedis()
	{
		return pool.getResource();
	}
}