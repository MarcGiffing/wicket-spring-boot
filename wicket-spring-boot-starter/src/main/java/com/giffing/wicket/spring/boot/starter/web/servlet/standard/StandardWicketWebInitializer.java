package com.giffing.wicket.spring.boot.starter.web.servlet.standard;

import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerConfig;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 * The {@link StandardWicketWebInitializer} will be configured when no other 
 * {@link WicketWebInitializerConfig} is present. It uses the standard {@link WicketFilter}.
 *  
 * @author Marc Giffing
 */
public class StandardWicketWebInitializer implements WicketWebInitializerConfig {

	@Override
	public WicketFilter createWicketFilter(WebApplication application) {
		return new WicketFilter(application);
	}

}
