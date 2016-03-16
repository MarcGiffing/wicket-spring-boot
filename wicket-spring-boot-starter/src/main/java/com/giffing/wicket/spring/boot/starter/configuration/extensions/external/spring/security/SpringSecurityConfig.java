package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = SpringSecurityProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing=true)
@ConditionalOnClass(value = {
		org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession.class,
		org.springframework.security.core.Authentication.class,
		org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter.class
})
@EnableConfigurationProperties({ SpringSecurityProperties.class })
public class SpringSecurityConfig {
	
	
	@Configuration
	@ConditionalOnMissingBean(WicketBootWebApplication.class)
	public static class WicketBootSecuredWebApplicationAuto {
		
		@Bean
		public WicketBootSecuredWebApplication wicketBootWebApplication() {
			return new WicketBootSecuredWebApplication();
		}
		
	}
	
	@Bean
	public AuthenticatedWebSessionConfig authenticatedWebSessionConfig(){
		return new AuthenticatedWebSessionConfig() {
			
			@Override
			public Class<? extends AbstractAuthenticatedWebSession> getAuthenticatedWebSessionClass() {
				return SecureWebSession.class;
			}
		};
	}


}
