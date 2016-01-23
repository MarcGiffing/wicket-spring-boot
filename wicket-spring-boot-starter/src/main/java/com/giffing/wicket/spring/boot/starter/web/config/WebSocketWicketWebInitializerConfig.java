package com.giffing.wicket.spring.boot.starter.web.config;

import javax.servlet.Filter;

import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(JavaxWebSocketFilter.class)
@ConditionalOnProperty(prefix = "wicket.external.websocket", value = "enabled", matchIfMissing = true)
public class WebSocketWicketWebInitializerConfig implements WicketWebInitializerConfig {

	@Override
	public Class<? extends Filter> filterClass() {
		return JavaxWebSocketFilter.class;
	}

}
