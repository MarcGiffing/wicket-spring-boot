package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.RuntimeConfigurationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.core.settings.general")
@Getter
@Setter
public class GeneralSettingsProperties {

    /**
     * Defines the configuration startup mode. It uses Wickets
     * {@link RuntimeConfigurationType}
     */
    private RuntimeConfigurationType configurationType = RuntimeConfigurationType.DEPLOYMENT;

}
