package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.JoinChatEvent;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.LeftChatEvent;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;

@Component
public class ChatService {

	private final WebSocketMessageBroadcaster broadcaster;
	
	private Set<String> participants = new HashSet<>();

	public ChatService(WebSocketMessageBroadcaster broadcaster) {
		this.broadcaster = broadcaster;
	}
	
	public Set<String> getParticipants() {
		return participants;
	}

	public void join(String username) {
		participants.add(username);
		broadcaster.sendToAll(new JoinChatEvent(username));
	}
	
	public void leave(String username) {
		participants.remove(username);
		broadcaster.sendToAll(new LeftChatEvent(username));
	}
	
}
