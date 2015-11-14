package com.giffing.wicket.spring.boot.starter.configuration.extensions.webjars;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;

import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;

@Component
@ConditionalOnProperty(prefix = "wicket.webjars", value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = de.agilecoders.wicket.webjars.WicketWebjars.class)
@EnableConfigurationProperties({ WebjarsProperties.class })
public class WebjarsConfig implements WicketApplicationInitConfiguration{

	@Override
	public void init(WebApplication webApplication) {
		WebjarsSettings settings = new WebjarsSettings();
        WicketWebjars.install(webApplication, settings);
	}

}
