package com.giffing.wicket.spring.boot.example.web.pages.websocket.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

/**
 * Used to send a message to a specific user
 */
@Getter
@RequiredArgsConstructor
public class CustomerMessageEvent implements IWebSocketPushMessage {

    private final String sender;

    private final String message;


}
