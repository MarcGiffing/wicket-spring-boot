package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.springboot.devtools;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SpringDevToolsProperties.PROPERTY_PREFIX)
public class SpringDevToolsProperties {

	public static final String PROPERTY_PREFIX = "spring.devtools.restart";
	
	private boolean enabled = true;
	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
