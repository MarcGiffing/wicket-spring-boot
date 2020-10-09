package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.web.html.border.LabeledFormBorder;
import com.giffing.wicket.spring.boot.example.web.html.panel.FeedbackPanel;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.events.CustomerMessageEvent;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;

@MountPath("chat")
@AuthorizeInstantiation("USER")
public class WebSocketPage extends BasePage {

	@SpringBean
	private WebSocketMessageBroadcaster broadcaster;
	
	public WebSocketPage() {
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
		form.add(new LabeledFormBorder<>(getString("username"), new RequiredTextField<>("username", to)));
		form.add(new LabeledFormBorder<>(getString("message"), new RequiredTextField<>("message", message)));
		form.add(new AjaxButton("submit") {

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				String name = SecurityContextHolder.getContext().getAuthentication().getName();
				broadcaster.sendTo(to.getObject(), new CustomerMessageEvent(name, message.getObject()));
			}

		});
	}

	private void addWebsocketBehaviour(FeedbackPanel feedbackPanel) {
		add(new WebSocketBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onPush(WebSocketRequestHandler handler, IWebSocketPushMessage message) {
				if (message instanceof CustomerMessageEvent) {
					CustomerMessageEvent event = (CustomerMessageEvent)message;
					info(event.getSender() + ": "+ event.getMessage());
					handler.add(feedbackPanel);
				}
			}

		});
	}
	
}
