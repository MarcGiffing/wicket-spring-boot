package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.exceptions;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.ExceptionSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

/**
 * Configuration options for Wickets exception settings. I don't know if someone needs this
 * settings and when they don't need my javadoc :-)
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = ExceptionSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({ ExceptionSettingsProperties.class })
public class ExceptionSettingsConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private ExceptionSettingsProperties props;
	
	@Override
	public void init(WebApplication webApplication) {
		ExceptionSettings exceptionSettings = webApplication.getExceptionSettings();
		
		exceptionSettings.setAjaxErrorHandlingStrategy(props.getErrorHandlingStrategyDuringAjaxRequests());
		exceptionSettings.setThreadDumpStrategy(props.getThreadDumpStrategy());
	}

}
