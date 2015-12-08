package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.requestmapper;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CryptMapperProperties.PROPERTY_PREFIX)
public class CryptMapperProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.requestmapper.cryptmapper";
}
