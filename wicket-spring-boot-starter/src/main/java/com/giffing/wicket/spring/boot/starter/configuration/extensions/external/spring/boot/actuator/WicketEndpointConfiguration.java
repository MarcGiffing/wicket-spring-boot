package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

@Configuration
public class WicketEndpointConfiguration {

	@Bean
	public WicketEndpoint wicketEndpoint() {
		return new WicketEndpoint(wicketEndpointRepositoryDefault());
	}
	
	@Bean
	@ConditionalOnMissingBean
	public WicketEndpointRepository wicketEndpointRepositoryDefault() {
		return new WicketEndpointRepositoryDefault();
	}
}
