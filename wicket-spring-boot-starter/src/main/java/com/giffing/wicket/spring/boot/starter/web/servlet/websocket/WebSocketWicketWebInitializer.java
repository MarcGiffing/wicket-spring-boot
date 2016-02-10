package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import javax.servlet.Filter;

import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;

import com.giffing.wicket.spring.boot.starter.web.config.WicketWebInitializerConfig;

/**
 * This initializer will be used if the JSR 356 - Java API for WebSocket is
 * used.
 * 
 * @author Marc Giffing
 */
public class WebSocketWicketWebInitializer implements WicketWebInitializerConfig {
	
	@Override
	public Class<? extends Filter> filterClass() {
		return JavaxWebSocketFilter.class;
	}
}
