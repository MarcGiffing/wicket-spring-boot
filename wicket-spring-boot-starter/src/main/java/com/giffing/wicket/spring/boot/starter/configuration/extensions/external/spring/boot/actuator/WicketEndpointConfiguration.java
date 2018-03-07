package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

@Configuration
public class WicketEndpointConfiguration {

	@Autowired
	private WicketEndpointRepository repo;
	
	@Bean
	public WicketEndpoint wicketEndpoint() {
		return new WicketEndpoint(repo);
	}
	
}
