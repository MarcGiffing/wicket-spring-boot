package com.giffing.wicket.spring.boot.starter.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

		@Bean
		public WicketServerEndpointConfigRegister myWicketServerEndpointConfig() {
			return new WicketServerEndpointConfigRegister();
		}

		@Bean
		public WebSocketMessageSenderImpl webSocketEventHandler() {
			return new WebSocketMessageSenderImpl();
		}

		public static class WicketServerEndpointConfigRegister implements ServletContextListener {

			private final static String SERVER_CONTAINER_ATTRIBUTE = "javax.websocket.server.ServerContainer";

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				ServletContext container = sce.getServletContext();

				final ServerContainer serverContainer = (ServerContainer) container
						.getAttribute(SERVER_CONTAINER_ATTRIBUTE);
				try {
					serverContainer.addEndpoint(new WicketServerEndpointConfig());
				} catch (DeploymentException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
			}
		}
		
	}

	
	
}
