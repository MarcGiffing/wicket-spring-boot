package com.giffing.wicket.spring.boot.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootStandardWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

/**
 * Configures the standard {@link WicketBootWebApplication} configuration
 * without security options.
 * 
 * The {@link WicketBootStandardWebApplication} will only be activated if there
 * is no other {@link WicketBootWebApplication} present and if there is not
 * {@link WicketBootSecuredWebApplication} present.
 * 
 * @author Marc Giffing
 *
 */
@Configuration
@ConditionalOnMissingBean(WicketBootSecuredWebApplication.class)
public class WicketBootWebApplicationAutoConfiguration {

	@Bean
	public WicketBootStandardWebApplication wicketBootWebApplication() {
		return new WicketBootStandardWebApplication();
	}

}
