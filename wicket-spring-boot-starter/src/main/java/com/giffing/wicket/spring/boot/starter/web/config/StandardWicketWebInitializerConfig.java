package com.giffing.wicket.spring.boot.starter.web.config;

import javax.servlet.Filter;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

@Configuration
//TODO check if no other WicketWebInitializerConfig bean is present
@ConditionalOnMissingClass("org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter")
@ConditionalOnMissingBean(WicketWebInitializerConfig.class)
public class StandardWicketWebInitializerConfig implements WicketWebInitializerConfig {

	@Override
	public Class<? extends Filter> filterClass() {
		return WicketFilter.class;
	}

}
