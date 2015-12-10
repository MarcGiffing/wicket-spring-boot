package com.giffing.wicket.spring.boot.starter;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket")
public class WicketProperties {

	/**
	 * Defines the configuration startup mode. It uses Wickets
	 * {@link RuntimeConfigurationType}
	 */
	private RuntimeConfigurationType configurationType = RuntimeConfigurationType.DEPLOYMENT;
	
	private RenderStrategy renderStrategy = RenderStrategy.REDIRECT_TO_BUFFER;
	
	private String defaultMarkupEncoding = "UTF-8";
	
	public RuntimeConfigurationType getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(RuntimeConfigurationType configurationType) {
		this.configurationType = configurationType;
	}

	public RenderStrategy getRenderStrategy() {
		return renderStrategy;
	}

	public void setRenderStrategy(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}

	public String getDefaultMarkupEncoding() {
		return defaultMarkupEncoding;
	}

	public void setDefaultMarkupEncoding(String defaultMarkupEncoding) {
		this.defaultMarkupEncoding = defaultMarkupEncoding;
	}

}
