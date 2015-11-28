package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.hazelcast;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.SessionUnit;

@ConfigurationProperties(prefix = DataStoreHazelcastProperties.PROPERTY_PREFIX)
public class DataStoreHazelcastProperties {

	public static final String PROPERTY_PREFIX = "wicket.datastore.hazelcast";

	private boolean enabled = true;

	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;

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

}
