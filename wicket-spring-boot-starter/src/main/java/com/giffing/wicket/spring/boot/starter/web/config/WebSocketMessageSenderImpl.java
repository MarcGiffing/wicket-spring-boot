package com.giffing.wicket.spring.boot.starter.web.config;

import java.util.Collection;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

public class WebSocketMessageSenderImpl implements WebSocketMessageSender {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void send(IWebSocketPushMessage event) {
		Application application = Application.get(WicketWebInitializer.WICKET_FILTERNAME);
		WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
		IWebSocketConnectionRegistry connectionRegistry = webSocketSettings.getConnectionRegistry();
		Collection<IWebSocketConnection> connections = connectionRegistry.getConnections(application);
		log.trace("sending event to {} connections", connections.size());
		for (IWebSocketConnection connection : connections) {
			connection.sendMessage(event);
		}
	}

}
