package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.springreference;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.springreference.SpringReferenceSupporter;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = SpringReferenceProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = SpringReferenceSupporter.class)
@EnableConfigurationProperties({ SpringReferenceProperties.class })
public class SpringReferenceConfig implements WicketApplicationInitConfiguration {
	
	@Autowired
	private SpringReferenceProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		SpringReferenceSupporter.register(webApplication);
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}

}
