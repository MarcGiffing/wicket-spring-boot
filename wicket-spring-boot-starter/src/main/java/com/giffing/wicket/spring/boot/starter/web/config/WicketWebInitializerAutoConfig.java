package com.giffing.wicket.spring.boot.starter.web.config;

import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

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
	@ConditionalOnClass({JavaxWebSocketFilter.class, ServerEndpointExporter.class, ServerEndpointExporter.class})
	@ConditionalOnProperty(prefix = "wicket.external.websocket", value = "enabled", matchIfMissing = true)
	public static class WebSocketWicketWebInitializerAutoConfiguration {

		@Bean
		public WicketWebInitializerConfig wicketWebInitializerConfig() {
			return new WebSocketWicketWebInitializer();
		}
		
		@Bean
		public ServerEndpointExporter serverEndpointExporter() {
			return new ServerEndpointExporter();
		}
		
		@Bean
		public WicketServerEndpointConfig myWicketServerEndpointConfig() {
			return new WicketServerEndpointConfig();
		}
		
		@Bean
		public WebSocketEventHandler webSocketEventHandler(){
			return new WebSocketEventHandler();
		}

	}
}
