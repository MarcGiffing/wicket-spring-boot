package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import com.giffing.wicket.spring.boot.example.web.html.panel.FeedbackPanel;
import com.giffing.wicket.spring.boot.example.web.pages.BaseAuthenticatedPage;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.CustomerMessageEvent;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.JoinChatEvent;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.events.LeftChatEvent;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;
import de.agilecoders.wicket.jquery.JQuery;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
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
import java.util.stream.Collectors;

@MountPath("chat")
@AuthorizeInstantiation("USER")
public class ChatPage extends BaseAuthenticatedPage {

    @SpringBean
    private WebSocketMessageBroadcaster webSocketMessageBroadcaster;

    @SpringBean
    private ChatService chatService;

    private WebMarkupContainer chatMessageContainer;
    private ListView<ChatMessage> messages;

    RequiredTextField<String> messageInput;

    private DropDownChoice participantChoice;

    public ChatPage(PageParameters pageParameters) {
        super(pageParameters);
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        addChatForm();
        addWebsocketBehaviour(feedbackPanel);
    }

    private void addChatForm() {
        IModel<String> to = Model.of("");
        IModel<String> message = Model.of("");
        Form form = null;
        add(form = new Form<>("form"));
        form.add(participantChoice = new DropDownChoice<>("participants", to, () -> chatService
                .getParticipants()
                .stream().collect(Collectors.toList())));
        participantChoice.setOutputMarkupId(true);
        messageInput = new RequiredTextField<>("message", message);
        messageInput.setOutputMarkupId(true);

        form.add(messageInput);
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
        form.add(submitButton);

        messages = new ListView<>("messages", new ArrayList<>()) {

            @Override
            protected void populateItem(ListItem<ChatMessage> item) {
                ChatMessage modelObject = item.getModelObject();
                item.add(new Label("from", modelObject.getFrom()));
                item.add(new Label("message", modelObject.getMessage()));

            }
        };
        messages.setOutputMarkupId(true);
        form.add(chatMessageContainer = new WebMarkupContainer("chatMessageContainer"));
        chatMessageContainer.setOutputMarkupId(true);
        chatMessageContainer.add(messages);
    }

    private void addWebsocketBehaviour(FeedbackPanel feedbackPanel) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        String browserTabIdentifier = UUID.randomUUID().toString();
        ChatParticipant chatParticipant = new ChatParticipant(browserTabIdentifier, currentUsername);
        add(new WebSocketBehavior() {

            @Override
            protected void onPush(WebSocketRequestHandler handler, IWebSocketPushMessage message) {
                if (message instanceof CustomerMessageEvent event) {
                    messages.getModelObject().add(new ChatMessage(event.getSender(), event.getMessage()));
                    handler.add(chatMessageContainer);
                }
                if (message instanceof JoinChatEvent event) {
                    String notifyStatus = "success";
                    String notifyMessage = event.getUsername() + " joined the chat";
                    addNotifyMessage(handler, notifyStatus, notifyMessage);
                    handler.add(participantChoice);
                }
                if (message instanceof LeftChatEvent event) {
                    String notifyStatus = "warning";
                    String notifyMessage = event.getUsername() + " left the chat";
                    addNotifyMessage(handler, notifyStatus, notifyMessage);
                    handler.add(participantChoice);
                }
            }

            private void addNotifyMessage(WebSocketRequestHandler handler, String notifyStatus, String notifyMessage) {
                handler.appendJavaScript(JQuery
                        .plain("noty({text: '" + notifyMessage + "', type: '" + notifyStatus
                                + "', timeout: '5000', progressbar: true});"));
            }

            protected void onConnect(ConnectedMessage message) {
                chatService.join(chatParticipant);
            }

            protected void onClose(ClosedMessage message) {
                chatService.leave(chatParticipant);
            }

        });
    }

    private static class ChatMessage implements Serializable {
        private final String from;
        private final String message;

        public ChatMessage(String from, String message) {
            this.from = from;
            this.message = message;
        }

        public String getFrom() {
            return from;
        }

        public String getMessage() {
            return message;
        }


    }

    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssReferenceHeaderItem.forReference(new PackageResourceReference(ChatPage.class, "ChatPage.css")));
    }

}
