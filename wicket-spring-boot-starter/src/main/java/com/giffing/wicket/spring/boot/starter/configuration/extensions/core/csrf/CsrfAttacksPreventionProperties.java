package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.csrf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CsrfAttacksPreventionProperties.PROPERTY_PREFIX)
public class CsrfAttacksPreventionProperties {
	
	public static  final String PROPERTY_PREFIX = "wicket.core.csrf";
	
	/**
	 * Enables Wickets CSRF protection
	 */
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
