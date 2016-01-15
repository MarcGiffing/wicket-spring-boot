package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requrestcycle;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = RequestCycleSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({ RequestCycleSettingsProperties.class })
public class RequestCycleSettingsConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private RequestCycleSettingsProperties properties;

	@Override
	public void init(WebApplication webApplication) {
		RequestCycleSettings requestCycleSettings = webApplication.getRequestCycleSettings();
		
		requestCycleSettings.setRenderStrategy(properties.getRenderStrategy());
		requestCycleSettings.setBufferResponse(properties.isBufferResponse());
		requestCycleSettings.setExceptionRetryCount(properties.getExceptionRetryCount());
		requestCycleSettings.setGatherExtendedBrowserInfo(properties.isGatherExtendedBrowserInfo());
		requestCycleSettings.setResponseRequestEncoding(properties.getResponseRequestEncoding());
		requestCycleSettings.setTimeout(TypeParser.parse(properties.getTimeoutSize(), properties.getTimeoutUnit()));
	}
	
}
