package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import com.giffing.wicket.spring.boot.example.web.html.panel.FeedbackPanel;
import com.giffing.wicket.spring.boot.example.web.pages.BaseAuthenticatedPage;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.CustomerMessageEvent;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.JoinChatEvent;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.LeftChatEvent;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;
import de.agilecoders.wicket.jquery.JQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.ClosedMessage;
import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@MountPath("chat")
@AuthorizeInstantiation("USER")
public class ChatPage extends BaseAuthenticatedPage {

    @SpringBean
    private WebSocketMessageBroadcaster webSocketMessageBroadcaster;

    @SpringBean
    private ChatService chatService;

    private WebMarkupContainer chatMessageContainer;

    private ListView<ChatMessage> messages;

    private RequiredTextField<String> messageInput;

    private DropDownChoice<String> participantChoice;

    public ChatPage(PageParameters pageParameters) {
        super(pageParameters);
        add(new FeedbackPanel("feedback").setOutputMarkupId(true));
        addWebsocketBehaviour();
        addChatForm();
    }

    private void addChatForm() {
        IModel<String> to = Model.of("");
        IModel<String> message = Model.of("");

        var form = new Form<Void>("form");

        participantChoice = new DropDownChoice<>("participants", to, () -> chatService.getParticipants().stream().toList());
        participantChoice.setOutputMarkupId(true);

        messageInput = new RequiredTextField<>("message", message);
        messageInput.setOutputMarkupId(true);
        AjaxButton submitButton = new AjaxButton("submit") {

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                webSocketMessageBroadcaster.sendToAll(new CustomerMessageEvent(name, message.getObject()));
                messageInput.setModelObject(null);
                target.add(messageInput);
            }

        };
        form.setDefaultButton(submitButton);


        messages = new ListView<>("messages", new ArrayList<>()) {

            @Override
            protected void populateItem(ListItem<ChatMessage> item) {
                ChatMessage modelObject = item.getModelObject();
                item.add(new Label("from", modelObject.getFrom()));
                item.add(new Label("message", modelObject.getMessage()));

            }
        };
        messages.setOutputMarkupId(true);

        chatMessageContainer = new WebMarkupContainer("chatMessageContainer");
        chatMessageContainer.setOutputMarkupId(true);
        chatMessageContainer.add(messages);

        add(form);
        form.add(submitButton);
        form.add(participantChoice);
        form.add(messageInput);
        form.add(chatMessageContainer);
    }

    private void addWebsocketBehaviour() {
        var currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        var browserTabIdentifier = UUID.randomUUID().toString();
        var chatParticipant = new ChatParticipant(browserTabIdentifier, currentUsername);
        add(new WebSocketBehavior() {

            @Override
            protected void onPush(WebSocketRequestHandler handler, IWebSocketPushMessage message) {
                if (message instanceof CustomerMessageEvent event) {
                    messages.getModelObject().add(new ChatMessage(event.getSender(), event.getMessage()));
                    handler.add(chatMessageContainer);
                }
                if (message instanceof JoinChatEvent event) {
                    var notifyStatus = "success";
                    var notifyMessage = "%s joined the chat".formatted(event.getUsername());
                    addNotifyMessage(handler, notifyStatus, notifyMessage);
                    handler.add(participantChoice);
                }
                if (message instanceof LeftChatEvent event) {
                    String notifyStatus = "warning";
                    String notifyMessage = "%s left the chat".formatted(event.getUsername());
                    addNotifyMessage(handler, notifyStatus, notifyMessage);
                    handler.add(participantChoice);
                }
            }

            private void addNotifyMessage(WebSocketRequestHandler handler, String notifyStatus, String notifyMessage) {
                handler.appendJavaScript(JQuery
                        .plain("noty({text: '%s', type: '%s', timeout: '5000', progressbar: true});"
                                .formatted(notifyMessage, notifyStatus)));
            }

            @Override
            protected void onConnect(ConnectedMessage message) {
                chatService.join(chatParticipant);
            }

            @Override
            protected void onClose(ClosedMessage message) {
                chatService.leave(chatParticipant);
            }

        });
    }

    @Getter
    @RequiredArgsConstructor
    private static class ChatMessage implements Serializable {
        private final String from;
        private final String message;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new PackageResourceReference(ChatPage.class, "ChatPage.css")));
    }

}
