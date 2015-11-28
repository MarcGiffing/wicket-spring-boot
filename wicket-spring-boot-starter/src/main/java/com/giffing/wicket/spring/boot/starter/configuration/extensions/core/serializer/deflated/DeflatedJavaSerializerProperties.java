package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.serializer.deflated;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DeflatedJavaSerializerProperties.PROPERTY_PREFIX)
public class DeflatedJavaSerializerProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.serializer.deflated";
	
	/**
	 * Toggle debug settings
	 */
	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
