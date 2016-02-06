package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.htmlvalidator;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = HTMLValidatorProperties.PROPERTY_PREFIX)
public class HTMLValidatorProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.htmlvalidator";
	
}
