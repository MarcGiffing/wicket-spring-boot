package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.resourcesettings.packageresourceguard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(PackageResourceGuardProperties.PROPERTY_PREFIX)
public class PackageResourceGuardProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.resourcesettings.packageresourceguard";
	
	private List<String> pattern = new ArrayList<>();

	public List<String> getPattern() {
		return pattern;
	}

	public void setPattern(List<String> pattern) {
		this.pattern = pattern;
	}
	
}
