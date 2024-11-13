package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.webjars;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WebjarsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class WebjarsProperties {

    public static final String PROPERTY_PREFIX = "wicket.external.webjars";

    /**
     * Enables webjars support
     */
    private boolean enabled = true;

}
