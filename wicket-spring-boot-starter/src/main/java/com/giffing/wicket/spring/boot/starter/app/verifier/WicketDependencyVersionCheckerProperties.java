package com.giffing.wicket.spring.boot.starter.app.verifier;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = WicketDependencyVersionCheckerProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class WicketDependencyVersionCheckerProperties {

    public static final String PROPERTY_PREFIX = "wicket.verifier.dependencies";

    private boolean enabled = true;

    private boolean throwExceptionOnDependencyVersionMismatch = true;

}
