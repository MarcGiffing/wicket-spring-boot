package com.giffing.wicket.spring.boot.starter.web.config;

import javax.servlet.Filter;

import org.apache.wicket.protocol.http.WicketFilter;

/**
 * The {@link StandardWicketWebInitializer} will be configured when no other 
 * {@link WicketWebInitializerConfig} is present. It uses the standard {@link WicketFilter}.
 *  
 * @author Marc Giffing
 */
public class StandardWicketWebInitializer implements WicketWebInitializerConfig{

	@Override
	public Class<? extends Filter> filterClass() {
		return WicketFilter.class;
	}

}
