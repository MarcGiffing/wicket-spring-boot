package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.dashboard;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DashboardProperties.PROPERTY_PREFIX)
public class DashboardProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.dashboard";
	
	private boolean enabled = true;

	private int columnCount = 2;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

}
