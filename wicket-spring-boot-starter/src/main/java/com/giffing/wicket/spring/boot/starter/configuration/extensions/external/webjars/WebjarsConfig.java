package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.webjars;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.condition.ConditionalOnWicket;
import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepository;

import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;

/**
 * Enables webjars support if the following two condition matches:
 * 
 * 1. The {@link WicketWebjars} is in the classpath.
 * 
 * 2. The property wicket.webjars.enabled is true (default = true)
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = WebjarsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = de.agilecoders.wicket.webjars.WicketWebjars.class)
@EnableConfigurationProperties({ WebjarsProperties.class })
//TODO only added for explanation purpose - could maybe deleted if we want to support Wicket prior 7
@ConditionalOnWicket(value=7)
public class WebjarsConfig implements WicketApplicationInitConfiguration{

	@Autowired
	private WebjarsProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		WebjarsSettings settings = new WebjarsSettings();
        WicketWebjars.install(webApplication, settings);
        
        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
        
	}

}
