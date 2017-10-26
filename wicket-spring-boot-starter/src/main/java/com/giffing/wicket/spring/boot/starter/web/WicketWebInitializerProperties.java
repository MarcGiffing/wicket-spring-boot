package com.giffing.wicket.spring.boot.starter.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WicketWebInitializerProperties.PROPERTY_PREFIX)
public class WicketWebInitializerProperties {

	public static final String PROPERTY_PREFIX = "wicket.web.servlet";
	
	private String filterMappingParam = "/*";
	
	//Adds posibility to add init parameters dynamically
	private Map<String, String> initParameters = new HashMap<>(); 

	private List<DispatcherType> dispatcherTypes = Arrays.asList( DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC );

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

	public List<DispatcherType> getDispatcherTypes() {
		return dispatcherTypes;
	}

	public void setDispatcherTypes(List<DispatcherType> dispatcherTypes) {
		this.dispatcherTypes = dispatcherTypes;
	}
}
