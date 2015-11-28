package com.giffing.wicket.spring.boot.starter.configuration.extensions.beanvalidation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = BeanValidationProperties.PROPERTY_PREFIX)
public class BeanValidationProperties {

	public static final String PROPERTY_PREFIX = "wicket.beanvalidation";
	
	/**
	 * Enables or disables the bean validation
	 */
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
