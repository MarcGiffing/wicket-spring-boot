package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerConfig;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;

/**
 * This initializer will be used if the JSR 356 - Java API for WebSocket is
 * used.
 *
 * @author Marc Giffing
 */
public class WebSocketWicketWebInitializer implements WicketWebInitializerConfig {

    @Override
    public WicketFilter createWicketFilter(WebApplication application) {
        return new JavaxWebSocketFilter(application);
    }

}
