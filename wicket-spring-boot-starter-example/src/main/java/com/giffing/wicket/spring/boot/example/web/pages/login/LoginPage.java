package com.giffing.wicket.spring.boot.example.web.pages.login;

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.example.web.html.form.focus.FocusBehaviour;
import com.giffing.wicket.spring.boot.example.web.html.panel.FeedbackPanel;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Default login page.
 *
 * @author Marc Giffing
 */
@WicketSignInPage
@MountPath("login")
public class LoginPage extends BasePage {

    public LoginPage(PageParameters parameters) {
        super(parameters);

        if (((AbstractAuthenticatedWebSession) getSession()).isSignedIn()) {
            continueToOriginalDestination();
            setResponsePage(CustomerListPage.class);
        }
        add(new LoginForm("loginForm"));
    }

    private class LoginForm extends StatelessForm<LoginForm> {

        private String username;

        private String password;

        public LoginForm(String id) {
            super(id);
            setModel(new CompoundPropertyModel<>(this));
            add(new FeedbackPanel("feedback"));

            queueFormComponent(usernameField());
            queueFormComponent(new PasswordTextField("password"));
        }

        private RequiredTextField<String> usernameField() {
            var usernameField = new RequiredTextField<String>("username");
            usernameField.setOutputMarkupId(true);
            usernameField.add(new FocusBehaviour());
            return usernameField;
        }

        @Override
        protected void onSubmit() {
            AuthenticatedWebSession session = AuthenticatedWebSession.get();
            if (session.signIn(username, password)) {
                setResponsePage(CustomerListPage.class);
            } else {
                error("Login failed");
            }
        }
    }
}
