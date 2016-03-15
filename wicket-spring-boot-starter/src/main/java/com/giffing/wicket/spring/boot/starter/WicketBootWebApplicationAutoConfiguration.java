package com.giffing.wicket.spring.boot.starter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootStandardWebApplication;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;

@Configuration
@ConditionalOnMissingBean(WicketBootSecuredWebApplication.class)
public class WicketBootWebApplicationAutoConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GeneralSettingsProperties generalSettingsProperties;

	@Autowired(required = false)
	private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();

	@Bean
	public WicketBootStandardWebApplication wicketBootWebApplication() {
		return new WicketBootStandardWebApplication(applicationContext, configurations, generalSettingsProperties);
	}

}
