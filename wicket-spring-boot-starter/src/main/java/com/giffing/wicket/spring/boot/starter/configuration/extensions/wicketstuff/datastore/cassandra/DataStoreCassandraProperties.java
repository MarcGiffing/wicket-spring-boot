package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.cassandra;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.DurationUnit;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.SessionUnit;

@ConfigurationProperties(prefix = DataStoreCassandraProperties.PROPERTY_PREFIX)
public class DataStoreCassandraProperties {

	public static final String PROPERTY_PREFIX = "wicket.datastore.cassandra";
	
	private boolean enabled = true;
	
	private List<String> contactPoints;
	
	private String tableName = "pagestore";
	
	private String keyspaceName = "wicket";
	
	private Long recordTtl = 30L;
	
	private DurationUnit recordTtlUnit = DurationUnit.MINUTES;
	
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKeyspaceName() {
		return keyspaceName;
	}

	public void setKeyspaceName(String keyspaceName) {
		this.keyspaceName = keyspaceName;
	}

	public List<String> getContactPoints() {
		return contactPoints;
	}

	public void setContactPoints(List<String> contactPoints) {
		this.contactPoints = contactPoints;
	}


}
