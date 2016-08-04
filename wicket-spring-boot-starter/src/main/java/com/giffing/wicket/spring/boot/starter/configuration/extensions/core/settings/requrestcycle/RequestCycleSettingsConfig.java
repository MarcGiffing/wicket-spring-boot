package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requrestcycle;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepository;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = RequestCycleSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({ RequestCycleSettingsProperties.class })
public class RequestCycleSettingsConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private RequestCycleSettingsProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;

	@Override
	public void init(WebApplication webApplication) {
		RequestCycleSettings requestCycleSettings = webApplication.getRequestCycleSettings();
		
		requestCycleSettings.setRenderStrategy(props.getRenderStrategy());
		requestCycleSettings.setBufferResponse(props.isBufferResponse());
		requestCycleSettings.setExceptionRetryCount(props.getExceptionRetryCount());
		requestCycleSettings.setGatherExtendedBrowserInfo(props.isGatherExtendedBrowserInfo());
		requestCycleSettings.setResponseRequestEncoding(props.getResponseRequestEncoding());
		requestCycleSettings.setTimeout(TypeParser.parse(props.getTimeoutSize(), props.getTimeoutUnit()));
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}
	
}
