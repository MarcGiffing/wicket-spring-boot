package com.giffing.wicket.spring.boot.starter.configuration.extensions.devutils.statelesschacker;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.devutils.statelesschecker")
public class StatelessCheckerProperties {

	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
