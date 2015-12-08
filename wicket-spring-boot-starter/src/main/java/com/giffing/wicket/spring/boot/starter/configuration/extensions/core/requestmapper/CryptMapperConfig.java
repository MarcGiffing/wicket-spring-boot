package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.requestmapper;

import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = CryptMapperProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ CryptMapperProperties.class })
@Order(ApplicationInitExtension.DEFAULT_PRECEDENCE + 100)
public class CryptMapperConfig implements WicketApplicationInitConfiguration {
	
	@Override
	public void init(WebApplication webApplication) {
		webApplication.setRootRequestMapper(new CryptoMapper(webApplication.getRootRequestMapper(), webApplication));
	}

}
