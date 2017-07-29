package com.giffing.wicket.spring.boot.starter.web.config;

import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.starter.web.servlet.standard.StandardWicketWebInitializer;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageSenderDefault;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketWicketWebInitializer;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WicketServerEndpointConfigRegister;

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

		@Bean
		public WicketWebInitializerConfig wicketWebInitializerConfig() {
			return new WebSocketWicketWebInitializer();
		}
		
		/**
		 * @return the wicket server endpoint config register which registers  the {@link WicketServerEndpointConfig}
		 */
		@Bean
		@ConditionalOnProperty(prefix = "wicket.external.websocket.registerServerEndpoint", value = "enabled", matchIfMissing = true)
		public WicketServerEndpointConfigRegister wicketServerEndpointConfigRegister() {
			return new WicketServerEndpointConfigRegister();
		}

		@Bean
		public WebSocketMessageSenderDefault webSocketEventHandler() {
			return new WebSocketMessageSenderDefault();
		}

	}

	
	
}
