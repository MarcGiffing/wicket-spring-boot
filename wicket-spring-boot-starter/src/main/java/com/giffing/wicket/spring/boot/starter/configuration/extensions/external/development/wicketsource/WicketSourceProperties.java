package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.wicketsource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WicketSourceProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class WicketSourceProperties {

	public static final String PROPERTY_PREFIX = "wicket.external.development.wicketsource";
	
	private boolean enabled = false;

}
