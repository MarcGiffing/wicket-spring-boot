package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

/**
 * Used to broadcast {@link IWebSocketPushMessage} messages.
 * 
 * @author Marc Giffing
 *
 */
public interface WebSocketMessageBroadcaster {

	/**
	 * Broadcasts a push message to the Wicket page (and it's components)
	 * associated with all connections. The components can then send messages or
	 * component updates to client by adding them to the target.
	 * 
	 * @param event
	 */
	public void send(IWebSocketPushMessage event);

}
