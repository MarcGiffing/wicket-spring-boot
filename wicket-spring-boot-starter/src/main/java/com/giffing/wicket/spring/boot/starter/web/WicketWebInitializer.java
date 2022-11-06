package com.giffing.wicket.spring.boot.starter.web;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerAutoConfig;
import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerConfig;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.EnumSet;

/**
 * Primary container configuration to configure Wicket requests.
 * 
 * @author Marc Giffing
 */
@Configuration
@Import(value = { WicketWebInitializerAutoConfig.class })
@EnableConfigurationProperties({ WicketWebInitializerProperties.class })
public class WicketWebInitializer {

	public static final String WICKET_FILTERNAME = "wicket-filter";

	@Bean
	public FilterRegistrationBean<WicketFilter> wicketFilter(
			WicketWebInitializerConfig wicketWebInitializerConfig,
			WicketWebInitializerProperties props,
			WicketEndpointRepository wicketEndpointRepository,
			WicketBootWebApplication wicketBootWebApplication
	) {
		WicketFilter wicketFilter = wicketWebInitializerConfig.createWicketFilter((WebApplication) wicketBootWebApplication);

		FilterRegistrationBean<WicketFilter> filter = new FilterRegistrationBean<>(wicketFilter);
		filter.setName(WICKET_FILTERNAME);
		filter.addUrlPatterns(props.getFilterMappingParam());
		filter.setDispatcherTypes(EnumSet.copyOf( props.getDispatcherTypes() ));
		filter.setMatchAfter(props.isFilterMatchAfter());

		filter.addInitParameter(WicketFilter.FILTER_MAPPING_PARAM, props.getFilterMappingParam());
		props.getInitParameters().forEach(filter::addInitParameter);

		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("wicketFilterName", WICKET_FILTERNAME)
				.withDetail("wicketFilterClass", wicketFilter.getClass())
				.withDetail("properties", props)
				.build());

		return filter;
	}

}

