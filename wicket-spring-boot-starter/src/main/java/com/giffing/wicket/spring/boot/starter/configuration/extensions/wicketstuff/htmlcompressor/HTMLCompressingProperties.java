package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.htmlcompressor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

@ConfigurationProperties(prefix = "wicket.wicketstuff.htmlcompressor")
public class HTMLCompressingProperties {

	/**
	 * Indicates if the HTML compression should be enabled. It is only enable if a
	 * HTML compression library is present.
	 * 
	 * @see HTMLCompressingConfig
	 */
	private boolean enabled = true;
	
	/**
	 * Additional native properties to set on the {@link HtmlCompressor} provider.
	 */
	private Map<String, Boolean> properties = new HashMap<String, Boolean>();

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Map<String, Boolean> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Boolean> properties) {
		this.properties = properties;
	}

}
