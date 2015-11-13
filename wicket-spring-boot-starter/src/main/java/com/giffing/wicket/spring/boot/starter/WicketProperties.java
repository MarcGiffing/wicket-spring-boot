package com.giffing.wicket.spring.boot.starter;

import org.apache.wicket.RuntimeConfigurationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket")
public class WicketProperties {

	private RuntimeConfigurationType configurationType = RuntimeConfigurationType.DEVELOPMENT;

	public RuntimeConfigurationType getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(RuntimeConfigurationType configurationType) {
		this.configurationType = configurationType;
	}

}
