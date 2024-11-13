package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.exceptions;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.ExceptionSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

/**
 * Configuration options for Wickets exception settings. I don't know if someone needs this
 * settings and when they don't need my javadoc :-)
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = ExceptionSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({ExceptionSettingsProperties.class})
@RequiredArgsConstructor
public class ExceptionSettingsConfig implements WicketApplicationInitConfiguration {

    private final ExceptionSettingsProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        var exceptionSettings = webApplication.getExceptionSettings();

        exceptionSettings.setAjaxErrorHandlingStrategy(props.getErrorHandlingStrategyDuringAjaxRequests());
        exceptionSettings.setThreadDumpStrategy(props.getThreadDumpStrategy());

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }

}
