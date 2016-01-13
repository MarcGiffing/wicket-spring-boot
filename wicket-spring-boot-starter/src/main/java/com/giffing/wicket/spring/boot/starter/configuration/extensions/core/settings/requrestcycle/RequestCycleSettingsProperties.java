package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requrestcycle;

import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.core.settings.requestcycle")
public class RequestCycleSettingsProperties {

	private RenderStrategy renderStrategy = RenderStrategy.REDIRECT_TO_BUFFER;
	
	public RenderStrategy getRenderStrategy() {
		return renderStrategy;
	}

	public void setRenderStrategy(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}
	
}
