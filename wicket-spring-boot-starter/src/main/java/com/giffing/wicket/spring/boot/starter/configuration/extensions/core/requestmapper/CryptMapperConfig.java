package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.requestmapper;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = CryptMapperProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ CryptMapperProperties.class })
@Order(ApplicationInitExtension.DEFAULT_PRECEDENCE + 100)
@RequiredArgsConstructor
public class CryptMapperConfig implements WicketApplicationInitConfiguration {
	
	private final CryptMapperProperties props;

	private final WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		webApplication.setRootRequestMapper(new CryptoMapper(webApplication.getRootRequestMapper(), webApplication));
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}

}
