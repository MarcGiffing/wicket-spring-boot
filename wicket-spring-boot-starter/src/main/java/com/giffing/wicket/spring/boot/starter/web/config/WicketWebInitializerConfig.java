package com.giffing.wicket.spring.boot.starter.web.config;

import javax.servlet.Filter;

public interface WicketWebInitializerConfig {
	
	public Class<? extends Filter> filterClass();
	
}
