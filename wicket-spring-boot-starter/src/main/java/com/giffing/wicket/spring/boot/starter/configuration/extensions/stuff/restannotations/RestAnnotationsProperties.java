package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.restannotations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = RestAnnotationsProperties.PROPERTY_PREFIX)
public class RestAnnotationsProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.restannotations";
	
	private boolean enabled = true;

	private String packagename;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
}
