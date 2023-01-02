package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = SpringSecurityProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing=true)
@ConditionalOnClass(value = {
		org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession.class,
		org.springframework.security.core.Authentication.class,
		org.springframework.security.web.SecurityFilterChain.class
})
@EnableConfigurationProperties({ SpringSecurityProperties.class })
@ConditionalOnMissingBean(WicketBootWebApplication.class)
public class SpringSecurityConfig {
	
	@Bean
	public WicketBootSecuredWebApplication wicketBootWebApplication() {
		return new WicketBootSecuredWebApplication();
	}
	
	@Bean
	public AuthenticatedWebSessionConfig authenticatedWebSessionConfig(){
		return () -> SecureWebSession.class;
	}
}
