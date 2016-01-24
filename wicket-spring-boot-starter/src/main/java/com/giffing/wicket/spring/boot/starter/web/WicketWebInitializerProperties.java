package com.giffing.wicket.spring.boot.starter.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WicketWebInitializerProperties.PROPERTY_PREFIX)
public class WicketWebInitializerProperties {

	public static final String PROPERTY_PREFIX = "wicket.web.servlet";
	
	private String filterMappingParam = "/*";
	
	//Adds posibility to add init parameters dynamically
	private Map<String, String> initParameters = new HashMap<>(); 

	public String getFilterMappingParam() {
		return filterMappingParam;
	}

	public void setFilterMappingParam(String filterMappingParam) {
		this.filterMappingParam = filterMappingParam;
	}

	public Map<String, String> getInitParameters() {
		return initParameters;
	}

	public void setInitParameters(Map<String, String> initParameters) {
		this.initParameters = initParameters;
	}
}
