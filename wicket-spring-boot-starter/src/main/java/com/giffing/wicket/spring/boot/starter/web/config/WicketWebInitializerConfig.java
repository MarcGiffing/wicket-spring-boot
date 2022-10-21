package com.giffing.wicket.spring.boot.starter.web.config;

import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 * Dynamic configuration support which will be used in {@link WicketWebInitializer}.
 * 
 * The configuration {@link WicketWebInitializerAutoConfig} is responsible to detect which {@link WicketWebInitializerConfig}
 * should be configured.
 * 
 * @author Marc Giffing
 */
public interface WicketWebInitializerConfig {
	
	/**
	 * @return a {@link WicketFilter} which will be configured in the {@link WicketWebInitializer}
	 */
	WicketFilter createWicketFilter(WebApplication application);
	
}
