package com.giffing.wicket.spring.boot.starter.web.config;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

public interface WebSocketMessageSender {

	public void send(IWebSocketPushMessage event);
	
}
