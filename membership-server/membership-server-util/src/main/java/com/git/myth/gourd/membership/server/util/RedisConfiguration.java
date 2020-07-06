package com.git.myth.gourd.membership.server.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class RedisConfiguration {

	private volatile String host;

    private volatile String password;
    
    private volatile int port;
    
    private volatile int timeout;
    
    private volatile int database;
    
    private GenericObjectPoolConfig pool;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public GenericObjectPoolConfig getPool() {
		return pool;
	}

	public void setPool(GenericObjectPoolConfig pool) {
		this.pool = pool;
	}
}
