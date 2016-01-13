package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.pagestore;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;

@ConfigurationProperties(StoreSettingsProperties.PROPERTY_PREFIX)
public class StoreSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.pagestore";
	
	/**
	 * Toggle debug settings
	 */
	private boolean enabled;
	
	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;
	
	private Boolean asynchronous;
	
	private Integer asynchronousQueueCapacity;
	
	private String fileStoreFolder;
	
	private Integer inmemoryCacheSize;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAsynchronous() {
		return asynchronous;
	}

	public void setAsynchronous(Boolean asynchronous) {
		this.asynchronous = asynchronous;
	}

	public Integer getAsynchronousQueueCapacity() {
		return asynchronousQueueCapacity;
	}

	public void setAsynchronousQueueCapacity(Integer asynchronousQueueCapacity) {
		this.asynchronousQueueCapacity = asynchronousQueueCapacity;
	}

	public String getFileStoreFolder() {
		return fileStoreFolder;
	}

	public void setFileStoreFolder(String fileStoreFolder) {
		this.fileStoreFolder = fileStoreFolder;
	}

	public Integer getInmemoryCacheSize() {
		return inmemoryCacheSize;
	}

	public void setInmemoryCacheSize(Integer inmemoryCacheSize) {
		this.inmemoryCacheSize = inmemoryCacheSize;
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
