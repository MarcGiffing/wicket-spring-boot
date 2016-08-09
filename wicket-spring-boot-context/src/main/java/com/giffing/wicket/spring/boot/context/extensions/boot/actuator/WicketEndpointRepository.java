package com.giffing.wicket.spring.boot.context.extensions.boot.actuator;

import java.util.List;

/**
 * This repository can be used to collect Wicket configuration information which can (e.g.) be displayed in 
 * a Spring Boot Actuator Wicket endpoint. 
 * 
 * @author Marc Giffing
 *
 */
public interface WicketEndpointRepository {

	void add(WicketAutoConfig autoconfig);
	
	List<WicketAutoConfig> getConfigs();
	
}
