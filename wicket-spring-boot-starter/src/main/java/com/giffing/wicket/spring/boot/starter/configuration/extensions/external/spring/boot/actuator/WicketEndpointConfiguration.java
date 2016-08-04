package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(value = {
		org.springframework.boot.actuate.endpoint.AbstractEndpoint.class,
})
public class WicketEndpointConfiguration {

	@Autowired
	private WicketEndpointRepository repo;
	
	@Bean
	public WicketEndpoint wicketEndpoint() {
		return new WicketEndpoint(repo);
	}
	
}
