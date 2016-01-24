package com.giffing.wicket.spring.boot.starter.web.config;

import javax.servlet.Filter;

import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

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
	 * @return a filter class which will be configured in the {@link WicketWebInitializer}
	 */
	public Class<? extends Filter> filterClass();
	
}
