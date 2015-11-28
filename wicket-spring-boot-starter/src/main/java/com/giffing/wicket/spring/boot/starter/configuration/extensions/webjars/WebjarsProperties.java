package com.giffing.wicket.spring.boot.starter.configuration.extensions.webjars;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WebjarsProperties.PROPERTY_PREFIX)
public class WebjarsProperties {
	
	public static final String PROPERTY_PREFIX = "wicket.webjars";
	
	/**
	 * Enables webjars support
	 */
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
