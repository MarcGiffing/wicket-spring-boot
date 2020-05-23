package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ResourceSettingsProperties.PROPERTY_PREFIX)
public class ResourceSettingsProperties {
	public static final String PROPERTY_PREFIX = "wicket.core.settings.resource";

	private boolean useMinifiedResources = true;
	private int resourcePollFrequencySeconds;

	public boolean isUseMinifiedResources()
	{
		return useMinifiedResources;
	}

	public void setUseMinifiedResources(boolean useMinifiedResources)
	{
		this.useMinifiedResources = useMinifiedResources;
	}

	public int getResourcePollFrequencySeconds()
	{
		return resourcePollFrequencySeconds;
	}

	public void setResourcePollFrequencySeconds(int resourcePollFrequencySeconds)
	{
		this.resourcePollFrequencySeconds = resourcePollFrequencySeconds;
	}
}