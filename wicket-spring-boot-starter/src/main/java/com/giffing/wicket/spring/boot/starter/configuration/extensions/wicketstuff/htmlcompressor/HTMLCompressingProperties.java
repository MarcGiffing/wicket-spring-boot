package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.htmlcompressor;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.wicketstuff.htmlcompressor")
public class HTMLCompressingProperties {

	/**
	 * Indicates if the HTML compression should be enabled. It is only enable if a
	 * HTML compression library is present.
	 * 
	 * @see HTMLCompressingConfig
	 */
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
