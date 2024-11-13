package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.shiro;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ShiroSecurityProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class ShiroSecurityProperties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.restannotations";

    private boolean enabled = true;

}
