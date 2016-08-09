package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;

@Component
public class WicketEndpointRepositoryDefault {

	public List<WicketAutoConfig> wicketAutoConfigurations = new ArrayList<>();
	
	public void add(WicketAutoConfig autoconfig) {
		this.wicketAutoConfigurations.add(autoconfig);
	}
	
	
}
