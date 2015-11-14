package com.giffing.wicket.spring.boot.starter.pages;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Default login page.
 * 
 * @author Marc Giffing
 *
 */
@MountPath("login")
public class LoginPage extends WebPage {

	public LoginPage(PageParameters parameters) {
		if (((AbstractAuthenticatedWebSession) getSession()).isSignedIn()) {
			continueToOriginalDestination();
		}
		add(new LoginForm("loginForm"));
	}

	private class LoginForm extends Form<Void> {

		private String username;
		
		@Size(min=5)
		private String password;

		public LoginForm(String id) {
			super(id);
			setModel(new CompoundPropertyModel(this));
			add(new FeedbackPanel("feedback"));
			add(new RequiredTextField<String>("username"));
			add(new PasswordTextField("password").add(new PropertyValidator<String>()));
		}

		@Override
		protected void onSubmit() {
			HttpServletRequest servletRequest = getServletRequest();
			AuthenticatedWebSession session = AuthenticatedWebSession.get();
			if (session.signIn(username, password)) {
				setResponsePage(getApplication().getHomePage());
			} else {
				error("Login failed");
			}
		}

		private HttpServletRequest getServletRequest() {
			return (HttpServletRequest) RequestCycle.get().getRequest()
				.getContainerRequest();
		}

	}

}
