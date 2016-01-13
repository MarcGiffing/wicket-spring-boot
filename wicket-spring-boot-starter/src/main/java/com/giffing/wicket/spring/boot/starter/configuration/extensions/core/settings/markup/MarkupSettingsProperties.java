package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.markup;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.core.settings.markup")
public class MarkupSettingsProperties {

	private String defaultMarkupEncoding = "UTF-8";

	public String getDefaultMarkupEncoding() {
		return defaultMarkupEncoding;
	}

	public void setDefaultMarkupEncoding(String defaultMarkupEncoding) {
		this.defaultMarkupEncoding = defaultMarkupEncoding;
	}

}
