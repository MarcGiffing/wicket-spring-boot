package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.debugsettings;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DebugSettingsProperties.PROPERTY_PREFIX)
public class DebugSettingsProperties {

	public static  final String PROPERTY_PREFIX = "wicket.core.debugsettings";
	
	/**
	 * Toggle debug settings
	 */
	private boolean enabled;
	
	private boolean developmentUtilitiesEnabled = true;
	
	private boolean ajaxDebugModeEnabled = false;
	
	private boolean componentUseCheck = false;
	
	private boolean outputMarkupContainerClassName = false;
	
	private String componentPathAttributeName;

	public boolean isDevelopmentUtilitiesEnabled() {
		return developmentUtilitiesEnabled;
	}

	public void setDevelopmentUtilitiesEnabled(boolean developmentUtilitiesEnabled) {
		this.developmentUtilitiesEnabled = developmentUtilitiesEnabled;
	}

	public boolean isAjaxDebugModeEnabled() {
		return ajaxDebugModeEnabled;
	}

	public void setAjaxDebugModeEnabled(boolean ajaxDebugModeEnabled) {
		this.ajaxDebugModeEnabled = ajaxDebugModeEnabled;
	}

	public boolean isComponentUseCheck() {
		return componentUseCheck;
	}

	public void setComponentUseCheck(boolean componentUseCheck) {
		this.componentUseCheck = componentUseCheck;
	}

	public boolean isOutputMarkupContainerClassName() {
		return outputMarkupContainerClassName;
	}

	public void setOutputMarkupContainerClassName(boolean outputMarkupContainerClassName) {
		this.outputMarkupContainerClassName = outputMarkupContainerClassName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getComponentPathAttributeName() {
		return componentPathAttributeName;
	}

	public void setComponentPathAttributeName(String componentPathAttributeName) {
		this.componentPathAttributeName = componentPathAttributeName;
	}
	
}
