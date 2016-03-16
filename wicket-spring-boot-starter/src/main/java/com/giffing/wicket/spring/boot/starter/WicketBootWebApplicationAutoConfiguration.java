package com.giffing.wicket.spring.boot.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootStandardWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

@Configuration
@ConditionalOnMissingBean(WicketBootSecuredWebApplication.class)
public class WicketBootWebApplicationAutoConfiguration {

	@Configuration
	@ConditionalOnMissingBean(WicketBootWebApplication.class)
	public static class WicketBootStandardWebApplicationAuto {
		@Bean
		public WicketBootStandardWebApplication wicketBootWebApplication() {
			return new WicketBootStandardWebApplication();
		}
	}

}
