package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.devutils.statelesschecker;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = StatelessCheckerProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class StatelessCheckerProperties {

	public static final String PROPERTY_PREFIX = "wicket.external.development.devutils.statelesschecker";
	
	private boolean enabled = false;

}
