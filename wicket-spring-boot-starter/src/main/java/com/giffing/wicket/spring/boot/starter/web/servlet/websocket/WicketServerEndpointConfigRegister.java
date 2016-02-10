package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerContainer;

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
