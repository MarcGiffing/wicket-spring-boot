package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WicketEndpointRepository {

	public List<WicketAutoConfig> wicketAutoConfigurations = new ArrayList<>();
	
	public void add(WicketAutoConfig autoconfig) {
		this.wicketAutoConfigurations.add(autoconfig);
	}
	
	
}
