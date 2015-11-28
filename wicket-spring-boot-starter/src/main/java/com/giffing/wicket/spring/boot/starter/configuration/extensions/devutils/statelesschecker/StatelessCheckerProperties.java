package com.giffing.wicket.spring.boot.starter.configuration.extensions.devutils.statelesschecker;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = StatelessCheckerProperties.PROPERTY_PREFIX)
public class StatelessCheckerProperties {

	public static final String PROPERTY_PREFIX = "wicket.devutils.statelesschecker";
	
	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
