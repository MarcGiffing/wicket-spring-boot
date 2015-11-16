package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketsource;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;

import net.ftlines.wicketsource.WicketSource;

/**
 * Enables wicket-source support if the following two condition matches:
 * 
 * 1. The {@link WicketSource} is in the classpath.
 * 
 * 2. The property wicket.wicketsource.enabled is true (default = true)
 * 
 * @author Marc Giffing
 *
 */
@Component
@ConditionalOnProperty(prefix = "wicket.wicketsource", value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = net.ftlines.wicketsource.WicketSource.class)
@EnableConfigurationProperties({ WicketSourceProperties.class })
public class WicketSourceConfig implements WicketApplicationInitConfiguration{

	@Override
	public void init(WebApplication webApplication) {
		WicketSource.configure(webApplication);
	}

}
