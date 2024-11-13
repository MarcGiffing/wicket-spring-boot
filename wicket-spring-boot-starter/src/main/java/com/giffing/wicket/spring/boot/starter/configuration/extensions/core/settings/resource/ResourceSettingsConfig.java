package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.resource;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

import java.time.Duration;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = ResourceSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ResourceSettingsProperties.class})
@RequiredArgsConstructor
public class ResourceSettingsConfig implements WicketApplicationInitConfiguration {

    private final ResourceSettingsProperties properties;

    @Override
    public void init(WebApplication webApplication) {
        webApplication.getResourceSettings().setUseMinifiedResources(properties.isUseMinifiedResources());
        if (properties.getResourcePollFrequencySeconds() > 0) {
            webApplication.getResourceSettings().setResourcePollFrequency(Duration.ofSeconds(properties.getResourcePollFrequencySeconds()));
        }
    }
}