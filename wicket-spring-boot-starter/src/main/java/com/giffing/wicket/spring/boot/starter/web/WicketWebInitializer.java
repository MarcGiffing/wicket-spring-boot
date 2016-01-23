package com.giffing.wicket.spring.boot.starter.web;

import javax.inject.Inject;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.starter.web.config.StandardWicketWebInitializerConfig;
import com.giffing.wicket.spring.boot.starter.web.config.WebSocketWicketWebInitializerConfig;
import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerConfig;

@Configuration
@Import(value = { StandardWicketWebInitializerConfig.class, WebSocketWicketWebInitializerConfig.class})
public class WicketWebInitializer implements ServletContextInitializer {

	@Inject
	private WicketWebInitializerConfig wicketWebInitializerConfig;

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		System.out.println(wicketWebInitializerConfig.filterClass());
		
		FilterRegistration filter = sc.addFilter("wicket-filter", wicketWebInitializerConfig.filterClass());
		filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
		filter.setInitParameter("applicationBean", "wicketBootWebApplication");
		filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
		filter.addMappingForUrlPatterns(null, false, "/*");
	}

}
