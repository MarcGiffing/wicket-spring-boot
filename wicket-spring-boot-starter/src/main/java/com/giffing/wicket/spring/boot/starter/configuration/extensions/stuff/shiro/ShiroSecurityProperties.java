package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ShiroSecurityProperties.PROPERTY_PREFIX)
public class ShiroSecurityProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.restannotations";
	
	private boolean enabled = true;

	
	public boolean isEnabled() {
		return enabled;
	}

	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
