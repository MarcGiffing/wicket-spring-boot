package com.giffing.wicket.spring.boot.starter.configuration;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * 
 * Spring configuration extensions should implement this interface to enable
 * wicket specific configuration wich should be executed in the init method.
 * 
 * @author Marc Giffing
 *
 */
public interface WicketApplicationInitConfiguration {

	/**
	 * With the given {@link WebApplication} the
	 * {@link WicketApplicationInitConfiguration}s can modify/extend the init()
	 * method of the {@link WebApplication}.
	 * 
	 * @param webApplication
	 *          the current {@link WebApplication}
	 */
	void init(WebApplication webApplication);

}
