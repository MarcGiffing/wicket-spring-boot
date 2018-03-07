package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;


@ConfigurationProperties(prefix = "endpoints.wicket")
@Endpoint(id = "wicket")
public class WicketEndpoint {

	private WicketEndpointRepository repository;

	public WicketEndpoint(WicketEndpointRepository repository) {
		this.repository = repository;
	}

	@ReadOperation
	public Map<String, Object> wicketExtensions() {
		Map<String, Object> result = new HashMap<>();
		result.put("extensions", repository.getConfigs());
		return result;
	}

}
