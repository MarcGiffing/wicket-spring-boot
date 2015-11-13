package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.statelesschecker;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.statelesschecker")
public class StatelessCheckerProperties {

	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
