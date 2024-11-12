package com.giffing.wicket.spring.boot.example.web.pages.websocket.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

@Getter
@RequiredArgsConstructor
public class JoinChatEvent implements IWebSocketPushMessage {

	private final String username;

}
