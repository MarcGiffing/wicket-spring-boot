package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.debug;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DebugSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DebugSettingsProperties {

    public static final String PROPERTY_PREFIX = "wicket.core.settings.debug";

    /**
     * Toggle debug settings
     */
    private boolean enabled;

    private boolean developmentUtilitiesEnabled = true;

    private boolean ajaxDebugModeEnabled = true;

    private boolean componentUseCheck = true;

    private boolean outputMarkupContainerClassName = false;

    private String componentPathAttributeName;

    private boolean linePreciseReportingOnNewComponentEnabled;

    private boolean linePreciseReportingOnAddComponentEnabled;

}