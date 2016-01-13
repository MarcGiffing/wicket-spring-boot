package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.types.DurationUnit;
import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;

@ConfigurationProperties(prefix = DataStoreRedisProperties.PROPERTY_PREFIX)
public class DataStoreRedisProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.datastore.redis";

	private boolean enabled = true;

	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;
	
	private String hostname = "127.0.0.1";
	
	private int port = 6379;
	
	private Long recordTtl = 30L;
	
	private DurationUnit recordTtlUnit = DurationUnit.MINUTES;
	

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

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Long getRecordTtl() {
		return recordTtl;
	}

	public void setRecordTtl(Long recordTtl) {
		this.recordTtl = recordTtl;
	}

	public DurationUnit getRecordTtlUnit() {
		return recordTtlUnit;
	}

	public void setRecordTtlUnit(DurationUnit recordTtlUnit) {
		this.recordTtlUnit = recordTtlUnit;
	}

}
