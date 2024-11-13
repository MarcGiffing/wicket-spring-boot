package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.beanvalidation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = BeanValidationProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class BeanValidationProperties {

    public static final String PROPERTY_PREFIX = "wicket.external.beanvalidation";

    /**
     * Enables or disables the bean validation
     */
    private boolean enabled;

}
