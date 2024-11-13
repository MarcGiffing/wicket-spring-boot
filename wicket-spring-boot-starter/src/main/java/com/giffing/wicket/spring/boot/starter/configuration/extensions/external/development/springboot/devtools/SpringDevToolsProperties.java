package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.springboot.devtools;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SpringDevToolsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class SpringDevToolsProperties {

    public static final String PROPERTY_PREFIX = "spring.devtools.restart";

    private boolean enabled = true;

}
