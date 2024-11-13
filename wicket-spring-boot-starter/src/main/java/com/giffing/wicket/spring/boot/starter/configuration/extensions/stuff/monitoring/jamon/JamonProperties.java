package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.monitoring.jamon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JamonProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class JamonProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.monitoring.jamon";
	
	private boolean includeSourceNameInMonitorLabel = true;
	
	private String mountPage = "/monitoring/jamon";

}
