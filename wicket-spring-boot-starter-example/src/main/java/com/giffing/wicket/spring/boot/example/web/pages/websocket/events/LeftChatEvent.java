package com.giffing.wicket.spring.boot.example.web.pages.websocket.events;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

public class LeftChatEvent implements IWebSocketPushMessage {

	private final String username;
	
	public LeftChatEvent(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
