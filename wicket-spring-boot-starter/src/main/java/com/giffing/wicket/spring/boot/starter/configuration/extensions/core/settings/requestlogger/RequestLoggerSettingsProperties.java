package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requestlogger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(RequestLoggerSettingsProperties.PROPERTY_PREFIX)
public class RequestLoggerSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.requestlogger";
	
	private boolean enabled = false;

	private boolean recordSessionSize = true;

	private int requestsWindowSize = 0;


	
	public boolean isRecordSessionSize() {
		return recordSessionSize;
	}

	
	public void setRecordSessionSize(boolean recordSessionSize) {
		this.recordSessionSize = recordSessionSize;
	}

	
	public int getRequestsWindowSize() {
		return requestsWindowSize;
	}

	
	public void setRequestsWindowSize(int requestsWindowSize) {
		this.requestsWindowSize = requestsWindowSize;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
