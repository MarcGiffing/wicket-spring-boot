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
	void sendToAll(IWebSocketPushMessage event);
	
	/**
	 * Sends a message to a specific identifier. The {@link WicketSessionResolver} is responsible to
	 * the identifier and return the corresponding session list to inform the user.
	 * 
	 *  The identifier can be an username or a user group. Its up to the user to decide how the session ids will
	 *  resolved.
	 */
	void sendTo(Object identifier, IWebSocketPushMessage event);
	
}
