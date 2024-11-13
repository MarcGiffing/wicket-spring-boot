package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requestlogger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(RequestLoggerSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class RequestLoggerSettingsProperties {

    public static final String PROPERTY_PREFIX = "wicket.core.settings.requestlogger";

    private boolean enabled = false;

    private boolean recordSessionSize = true;

    private int requestsWindowSize = 0;

}
