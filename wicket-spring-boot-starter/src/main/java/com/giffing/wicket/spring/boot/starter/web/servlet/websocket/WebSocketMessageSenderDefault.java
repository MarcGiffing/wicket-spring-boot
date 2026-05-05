package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import java.util.Collection;
import java.util.Objects;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
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
        var webSocketSettings = WebSocketSettings.Holder.get(application);
        var connectionRegistry = webSocketSettings.getConnectionRegistry();
        var connections = connectionRegistry.getConnections(application);
        sendMessage(event, connections);
    }

	private void sendMessage(IWebSocketPushMessage event, Collection<IWebSocketConnection> connections) {
		log.trace("sending event to {} connections", connections.size());
		connections.stream()
        .filter(Objects::nonNull)
        .forEach(connection ->
       	    connection.sendMessage(event)
        );
	}

    @Override
    public void sendTo(Object identifier, IWebSocketPushMessage event) {
        if (identifier == null) {
            return;
        }

        var webSocketSettings = WebSocketSettings.Holder.get(application);
        var connectionRegistry = webSocketSettings.getConnectionRegistry();
        wicketSessionResolver.resolve(identifier).forEach(sessionId -> {
            var connections = connectionRegistry.getConnections(application, sessionId);
            sendMessage(event, connections);
        });
    }
}
