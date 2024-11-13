package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.JoinChatEvent;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.LeftChatEvent;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ChatService {

    private final WebSocketMessageBroadcaster broadcaster;


    private List<ChatParticipant> participants = new ArrayList<>();

    public ChatService(WebSocketMessageBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public Set<String> getParticipants() {
        return participants
                .stream()
                .map(ChatParticipant::getUsername)
                .collect(Collectors.toSet());
    }

    public void join(ChatParticipant chatParticipant) {
        var username = chatParticipant.getUsername();
        var existingUserSpecificParticipants = participants
                .stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(chatParticipant.getUsername()))
                .toList();
        participants.add(chatParticipant);
        if (existingUserSpecificParticipants.isEmpty()) {
            broadcaster.sendToAll(new JoinChatEvent(username));
        }
    }

    public void leave(ChatParticipant chatParticipant) {
        var username = chatParticipant.getUsername();
        var chatParticipateToDelete = participants
                .stream()
                .filter(p -> p.getBrowserTabIdentifier().equals(chatParticipant.getBrowserTabIdentifier()))
                .findAny();
        chatParticipateToDelete.ifPresent(participant -> participants.remove(participant));

        var remainingUserSpecificParticipants = participants
                .stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(chatParticipant.getUsername()))
                .toList();
        if (remainingUserSpecificParticipants.isEmpty()) {
            broadcaster.sendToAll(new LeftChatEvent(username));
        }
    }

}
