package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.monitoring.jamon;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JamonProperties.PROPERTY_PREFIX)
public class JamonProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.monitoring.jamon";
	
	private boolean includeSourceNameInMonitorLabel = true;
	
	private String mountPage = "/monitoring/jamon";

	public String getMountPage() {
		return mountPage;
	}

	public void setMountPage(String mountPage) {
		this.mountPage = mountPage;
	}

	public boolean isIncludeSourceNameInMonitorLabel() {
		return includeSourceNameInMonitorLabel;
	}

	public void setIncludeSourceNameInMonitorLabel(boolean includeSourceNameInMonitorLabel) {
		this.includeSourceNameInMonitorLabel = includeSourceNameInMonitorLabel;
	}
}
