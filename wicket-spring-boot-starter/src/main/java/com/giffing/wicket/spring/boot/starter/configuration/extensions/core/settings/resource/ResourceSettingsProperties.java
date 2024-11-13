package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ResourceSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class ResourceSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.resource";

	private boolean useMinifiedResources = true;

	private int resourcePollFrequencySeconds;

}