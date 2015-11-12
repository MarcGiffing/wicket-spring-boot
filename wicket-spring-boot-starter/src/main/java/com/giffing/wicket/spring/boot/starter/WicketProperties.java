package com.giffing.wicket.spring.boot.starter;

import org.apache.wicket.RuntimeConfigurationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket")
public class WicketProperties {

	private RuntimeConfigurationType configurationType = RuntimeConfigurationType.DEVELOPMENT;

	/**
	 * Indicates if the HTML compression should be enabled. It is only enable if a
	 * HTML compression library is present.
	 */
	private boolean compressHTMLEnabled = true;

	private boolean statelessCheckerEnabled = false;

	public boolean getCompressHTMLEnabled() {
		return compressHTMLEnabled;
	}

	public void setCompressHTMLEnabled(boolean compressHTMLEnabled) {
		this.compressHTMLEnabled = compressHTMLEnabled;
	}

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
