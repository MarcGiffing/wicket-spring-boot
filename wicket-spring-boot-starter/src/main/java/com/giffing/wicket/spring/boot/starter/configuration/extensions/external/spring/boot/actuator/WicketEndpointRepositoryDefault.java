package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

@Component
public class WicketEndpointRepositoryDefault implements WicketEndpointRepository {

	public List<WicketAutoConfig> wicketAutoConfigurations = new ArrayList<>();
	
	public void add(WicketAutoConfig autoconfig) {
		this.wicketAutoConfigurations.add(autoconfig);
	}

	@Override
	public List<WicketAutoConfig> getConfigs() {
		return wicketAutoConfigurations;
	}
	
}
