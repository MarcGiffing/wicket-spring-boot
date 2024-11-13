package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.restannotations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = RestAnnotationsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class RestAnnotationsProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.restannotations";
	
	private boolean enabled = true;

	private String packagename;

}
