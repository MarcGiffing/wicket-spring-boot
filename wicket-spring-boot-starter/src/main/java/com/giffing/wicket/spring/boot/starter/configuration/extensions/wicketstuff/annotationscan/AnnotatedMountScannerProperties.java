package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.annotationscan;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AnnotatedMountScannerProperties.PROPERTY_PREFIX)
public class AnnotatedMountScannerProperties {

	public static final String PROPERTY_PREFIX = "wicket.wicketstuff.annotationscan";
	
	/**
	 * @see AnnotatedMountScannerConfig
	 */
	private boolean enabled = true;

	/**
	 * An alternative package name for scanning for mount path if the
	 * WicketApplication should not used as the root scan package
	 */
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
