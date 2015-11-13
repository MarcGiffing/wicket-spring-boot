package com.giffing.wicket.spring.boot.starter;

import org.apache.wicket.RuntimeConfigurationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket")
public class WicketProperties {

	private RuntimeConfigurationType configurationType = RuntimeConfigurationType.DEVELOPMENT;

	private boolean statelessCheckerEnabled = false;

	public RuntimeConfigurationType getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(RuntimeConfigurationType configurationType) {
		this.configurationType = configurationType;
	}

	public boolean isStatelessCheckerEnabled() {
		return statelessCheckerEnabled;
	}

	public void setStatelessCheckerEnabled(boolean statelessCheckerEnabled) {
		this.statelessCheckerEnabled = statelessCheckerEnabled;
	}

}
