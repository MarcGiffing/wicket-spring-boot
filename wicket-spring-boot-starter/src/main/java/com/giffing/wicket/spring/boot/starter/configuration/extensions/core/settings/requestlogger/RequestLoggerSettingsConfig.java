package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requestlogger;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.ExceptionSettings;
import org.apache.wicket.settings.RequestLoggerSettings;
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
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = RequestLoggerSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ RequestLoggerSettingsProperties.class })
public class RequestLoggerSettingsConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private RequestLoggerSettingsProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		RequestLoggerSettings wicketsRequestLoggerSettings = webApplication.getRequestLoggerSettings();
		
		wicketsRequestLoggerSettings.setRequestLoggerEnabled( props.isEnabled() );
		wicketsRequestLoggerSettings.setRecordSessionSize( props.isRecordSessionSize()  );
		wicketsRequestLoggerSettings.setRequestsWindowSize( props.getRequestsWindowSize() );
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}

}
