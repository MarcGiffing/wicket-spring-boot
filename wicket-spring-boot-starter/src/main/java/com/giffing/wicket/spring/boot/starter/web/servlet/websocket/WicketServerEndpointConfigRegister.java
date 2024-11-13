package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerContainer;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;

/**
 * Automatically registers the {@link WicketServerEndpointConfig} as an {@link Endpoint}
 * in the {@link ServerContainer}
 *
 * @author Marc Giffing
 */
public class WicketServerEndpointConfigRegister implements ServletContextListener {

    private static final String SERVER_CONTAINER_ATTRIBUTE = ServerContainer.class.getName();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var container = sce.getServletContext();

        var serverContainer = (ServerContainer) container.getAttribute(SERVER_CONTAINER_ATTRIBUTE);
        try {
            serverContainer.addEndpoint(new WicketServerEndpointConfig());
        } catch (DeploymentException e) {
            throw new WicketSpringBootException(e);
        }
    }

}
