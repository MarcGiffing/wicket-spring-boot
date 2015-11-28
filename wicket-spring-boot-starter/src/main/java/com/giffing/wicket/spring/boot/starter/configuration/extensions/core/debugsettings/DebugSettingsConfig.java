package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.debugsettings;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.DebugSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

/**
 * Enables debug settings if the following condition matches.
 * 
 * 1. The property {@link DebugSettingsProperties#PROPERTY_PREFIX}.enabled has to be true (default = false)
 *
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DebugSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ DebugSettingsProperties.class })
public class DebugSettingsConfig implements WicketApplicationInitConfiguration {
	
	@Autowired
	private DebugSettingsProperties properties;
	
	@Override
	public void init(WebApplication webApplication) {
		if(properties.isEnabled()){
			DebugSettings debugSettings = webApplication.getDebugSettings();
			debugSettings.setDevelopmentUtilitiesEnabled(properties.isDevelopmentUtilitiesEnabled());
			debugSettings.setAjaxDebugModeEnabled(properties.isAjaxDebugModeEnabled());
			debugSettings.setComponentUseCheck(properties.isComponentUseCheck());
			debugSettings.setOutputMarkupContainerClassName(properties.isOutputMarkupContainerClassName());
			debugSettings.setComponentPathAttributeName(properties.getComponentPathAttributeName());
		}
	}

}
