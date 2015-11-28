package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.memcached;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.DurationUnit;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.SessionUnit;

@ConfigurationProperties(prefix = DataStoreMemcachedProperties.PROPERTY_PREFIX)
public class DataStoreMemcachedProperties {

	public static final String PROPERTY_PREFIX = "wicket.datastore.memcached";

	private boolean enabled = true;

	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;
	
	private int expirationTime  = 30;
	
	private int port = 11211;
	
	private String serverNames;
	
	private long shutdownTimeout = 30;
	
	private DurationUnit shutdownTimeoutUnit  = DurationUnit.MINUTES;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getSessionSize() {
		return sessionSize;
	}

	public void setSessionSize(Long sessionSize) {
		this.sessionSize = sessionSize;
	}

	public SessionUnit getSessionUnit() {
		return sessionUnit;
	}

	public void setSessionUnit(SessionUnit sessionUnit) {
		this.sessionUnit = sessionUnit;
	}

	public int getExpirationTime() {
		return expirationTime;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getServerNames() {
		return serverNames;
	}

	public void setServerNames(String serverNames) {
		this.serverNames = serverNames;
	}

	public long getShutdownTimeout() {
		return shutdownTimeout;
	}

	public void setShutdownTimeout(long shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
	}

	public DurationUnit getShutdownTimeoutUnit() {
		return shutdownTimeoutUnit;
	}

	public void setShutdownTimeoutUnit(DurationUnit shutdownTimeoutUnit) {
		this.shutdownTimeoutUnit = shutdownTimeoutUnit;
	}

	public void setExpirationTime(int expirationTime) {
		this.expirationTime = expirationTime;
	}

}
