package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.springreference;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SpringReferenceProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class SpringReferenceProperties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.springreference";

    private boolean enabled = true;

}
