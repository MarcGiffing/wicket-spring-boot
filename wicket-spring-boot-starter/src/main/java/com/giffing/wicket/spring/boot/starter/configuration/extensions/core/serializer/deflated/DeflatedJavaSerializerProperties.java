package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.serializer.deflated;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DeflatedJavaSerializerProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DeflatedJavaSerializerProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.serializer.deflated";
	
	/**
	 * Toggle debug settings
	 */
	private boolean enabled = false;

}
