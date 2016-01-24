package com.giffing.wicket.spring.boot.starter.web;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerAutoConfig;
import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerConfig;

/**
 * Primary container configuration to configure Wicket requests.
 * 
 * @author Marc Giffing
 */
@Configuration
@Import(value = { WicketWebInitializerAutoConfig.class })
@EnableConfigurationProperties({ WicketWebInitializerProperties.class })
public class WicketWebInitializer implements ServletContextInitializer {

	@Autowired
	private WicketWebInitializerConfig wicketWebInitializerConfig;

	@Autowired
	private WicketWebInitializerProperties props;

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		FilterRegistration filter = sc.addFilter("wicket-filter", wicketWebInitializerConfig.filterClass());

		// Spring configuration
		filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
		filter.setInitParameter("applicationBean", "wicketBootWebApplication");

		filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, props.getFilterMappingParam());
		filter.addMappingForUrlPatterns(null, false, "/*");

		Map<String, String> initParameters = props.getInitParameters();
		for (Entry<String, String> initParam : initParameters.entrySet()) {
			filter.setInitParameter(initParam.getKey(), initParam.getValue());
		}

	}

}
