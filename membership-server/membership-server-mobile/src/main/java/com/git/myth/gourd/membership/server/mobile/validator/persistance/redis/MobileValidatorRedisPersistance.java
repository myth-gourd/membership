package com.git.myth.gourd.membership.server.mobile.validator.persistance.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.git.myth.gourd.membership.server.mobile.validator.configuration.MobileValidatorConfiguration;
import com.git.myth.gourd.membership.server.mobile.validator.controller.MobileValidatorController;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.MobileValidatorPersistance;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class MobileValidatorRedisPersistance implements MobileValidatorPersistance {

	private static final Logger LOG = LoggerFactory.getLogger(MobileValidatorController.class);

	@Autowired
	private MobileValidatorRedisPool pool;

	@Autowired
	private MobileValidatorConfiguration config;

	@Override
	public void save(String mobileNumber, String validatorCode) {
		Jedis jedis = null;
		try {
			jedis = pool.getJedis();
			jedis.setex(mobileNumber, config.getExpired(), validatorCode);
		} catch (JedisConnectionException ept) {
			LOG.warn("Mobile validator Redis configration error, Can not get resource from pool", ept);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public String findValidatorCode(String mobileNumber) {
		Jedis jedis = pool.getJedis();
		String code = jedis.get(mobileNumber);
		jedis.close();
		return code;
	}

	@Override
	public void delete(String mobileNumber) {
		Jedis jedis = pool.getJedis();
		jedis.del(mobileNumber);
		jedis.close();
	}
}