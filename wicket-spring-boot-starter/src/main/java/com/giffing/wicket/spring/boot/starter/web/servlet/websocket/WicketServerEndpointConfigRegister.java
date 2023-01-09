package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerContainer;

import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;

/**
 * Automatically registers the {@link WicketServerEndpointConfig} as an {@link Endpoint}
 * in the {@link ServerContainer}
 * 
 * @author Marc Giffing
 *
 */
public class WicketServerEndpointConfigRegister implements ServletContextListener {

	private final static String SERVER_CONTAINER_ATTRIBUTE = ServerContainer.class.getName();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext container = sce.getServletContext();

		final ServerContainer serverContainer = (ServerContainer) container
				.getAttribute(SERVER_CONTAINER_ATTRIBUTE);
		try {
			serverContainer.addEndpoint(new WicketServerEndpointConfig());
		} catch (DeploymentException e) {
			throw new WicketSpringBootException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
