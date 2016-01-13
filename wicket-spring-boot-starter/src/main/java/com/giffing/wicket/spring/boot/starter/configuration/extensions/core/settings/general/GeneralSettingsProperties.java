package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general;

import org.apache.wicket.RuntimeConfigurationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.core.settings.general")
public class GeneralSettingsProperties {

	/**
	 * Defines the configuration startup mode. It uses Wickets
	 * {@link RuntimeConfigurationType}
	 */
	private RuntimeConfigurationType configurationType = RuntimeConfigurationType.DEPLOYMENT;
	
	public RuntimeConfigurationType getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(RuntimeConfigurationType configurationType) {
		this.configurationType = configurationType;
	}
	
}
