package com.giffing.wicket.spring.boot.starter.configuration.extensions.devutils.inspector;

import org.apache.wicket.devutils.inspector.LiveSessionsPage;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket.devutils.interceptor")
public class InspectorProperties {
	
	/**
	 * Enables or disabled the mounting of the {@link LiveSessionsPage}.
	 */
	private boolean enableLiveSessionsPage;
	
	/**
	 * The relative address on which the {@link LiveSessionsPage} should be mounted 
	 */
	private String liveSessionPageMount = "devutils/inspector/live-session-page";
	
	public boolean isEnableLiveSessionsPage() {
		return enableLiveSessionsPage;
	}

	public void setEnableLiveSessionsPage(boolean enableLiveSessionsPage) {
		this.enableLiveSessionsPage = enableLiveSessionsPage;
	}

	public String getLiveSessionPageMount() {
		return liveSessionPageMount;
	}

	public void setLiveSessionPageMount(String liveSessionPageMount) {
		this.liveSessionPageMount = liveSessionPageMount;
	}
	
}
