package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SpringSecurityProperties.PROPERTY_PREFIX)
public class SpringSecurityProperties {

	public static final String PROPERTY_PREFIX = "wicket.external.spring.security";
	
}
