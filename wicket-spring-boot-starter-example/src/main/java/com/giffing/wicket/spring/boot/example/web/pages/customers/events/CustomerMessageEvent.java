package com.giffing.wicket.spring.boot.example.web.pages.customers.events;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

/**
 * Used to send a message to a specific user
 */
public class CustomerMessageEvent implements IWebSocketPushMessage {

	private static final long serialVersionUID = 1L;

	private final String sender;

	private final String message;

	public CustomerMessageEvent(String sender, String message) {
		this.sender = sender;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getSender() {
		return sender;
	}

}
