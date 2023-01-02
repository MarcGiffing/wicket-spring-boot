package com.giffing.wicket.spring.boot.starter.web.config;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.starter.web.servlet.standard.StandardWicketWebInitializer;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.DummyWicketSessionResolver;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageSenderDefault;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketWicketWebInitializer;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WicketServerEndpointConfigRegister;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WicketSessionResolver;

/**
 * Configuration of the {@link WicketWebInitializerConfig} depending on property
 * conditions and dependency existence.
 * 
 * @author Marc Giffing
 */
@Configuration
public class WicketWebInitializerAutoConfig {

	/**
	 * The {@link StandardWicketWebInitializer} should be configured if no other
	 * {@link WicketWebInitializerConfig} is configured. Its quasi the standard
	 * configuration.
	 * 
	 * @author Marc Giffing
	 */
	@Configuration
	@ConditionalOnMissingBean(WicketWebInitializerConfig.class)
	@AutoConfigureAfter(WebSocketWicketWebInitializerAutoConfiguration.class)
	public static class StandardWicketWebInitializerAutoConfiguration {

		@Bean
		public WicketWebInitializerConfig wicketWebInitializerConfig() {
			return new StandardWicketWebInitializer();
		}
	}

	/**
	 * @author Marc Giffing
	 */
	@Configuration
	@ConditionalOnClass({ JavaxWebSocketFilter.class })
	@ConditionalOnProperty(prefix = "wicket.external.websocket", value = "enabled", matchIfMissing = true)
	public static class WebSocketWicketWebInitializerAutoConfiguration {

		public static final String REGISTER_SERVER_ENDPOINT = "wicket.external.websocket.registerServerEndpoint";
		public static final String REGISTER_SERVER_ENDPOINT_ENABLED = REGISTER_SERVER_ENDPOINT + ".enabled";
		
		@Bean
		public WicketWebInitializerConfig wicketWebInitializerConfig() {
			return new WebSocketWicketWebInitializer();
		}
		
		/**
		 * @return the wicket server endpoint config register which registers  the {@link WicketServerEndpointConfig}
		 */
		@Bean
		@ConditionalOnProperty(prefix = REGISTER_SERVER_ENDPOINT, value = "enabled", matchIfMissing = true)
		public WicketServerEndpointConfigRegister wicketServerEndpointConfigRegister() {
			return new WicketServerEndpointConfigRegister();
		}

		@Bean
		@ConditionalOnMissingBean(WicketSessionResolver.class)
		public WicketSessionResolver dummyWicketSessionResolver() {
			return new DummyWicketSessionResolver();
		}
		
		@Bean
		public WebSocketMessageSenderDefault webSocketEventHandler(Application application, WicketSessionResolver wicketSessionResolver) {
			return new WebSocketMessageSenderDefault(application, wicketSessionResolver);
		}

	}

	
	
}
