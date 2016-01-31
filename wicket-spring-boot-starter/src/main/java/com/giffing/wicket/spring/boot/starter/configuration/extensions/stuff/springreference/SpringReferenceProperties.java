package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.springreference;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SpringReferenceProperties.PROPERTY_PREFIX)
public class SpringReferenceProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.springreference";
	
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
