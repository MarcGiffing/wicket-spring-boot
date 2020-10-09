package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import java.util.Collection;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketMessageSenderDefault implements WebSocketMessageBroadcaster {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Application application;

	private WicketSessionResolver wicketSessionResolver;

	public WebSocketMessageSenderDefault(Application application, WicketSessionResolver wicketSessionResolver) {
		this.application = application;
		this.wicketSessionResolver = wicketSessionResolver;
	}
	
	@Override
	public void sendToAll(IWebSocketPushMessage event) {
		WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
		IWebSocketConnectionRegistry connectionRegistry = webSocketSettings.getConnectionRegistry();
		Collection<IWebSocketConnection> connections = connectionRegistry.getConnections(application);
		log.trace("sending event to {} connections", connections.size());
		for (IWebSocketConnection connection : connections) {
			connection.sendMessage(event);
		}
	}
	
	public void sendTo(Object identifier, IWebSocketPushMessage event) {
		Application application = Application.get();
		WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
		IWebSocketConnectionRegistry connectionRegistry = webSocketSettings.getConnectionRegistry();
		wicketSessionResolver.resolve(identifier).forEach(sessionId -> {
			Collection<IWebSocketConnection> connections = connectionRegistry.getConnections(application, sessionId);
			log.trace("sending event to {} connections", connections.size());
			for (IWebSocketConnection connection : connections) {
				connection.sendMessage(event);
			}
		});
	}

}
