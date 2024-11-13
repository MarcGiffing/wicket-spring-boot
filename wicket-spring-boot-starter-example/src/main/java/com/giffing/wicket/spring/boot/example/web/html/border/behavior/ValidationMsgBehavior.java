package com.giffing.wicket.spring.boot.example.web.html.border.behavior;


import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.border.BorderBehavior;
import org.apache.wicket.markup.html.form.FormComponent;

public class ValidationMsgBehavior extends BorderBehavior {

    private static final long serialVersionUID = 1L;

    @Override
    public void beforeRender(Component c) {
        if (c instanceof FormComponent<?> fc && !fc.isValid()) {
            super.beforeRender(c);
        }
    }

    @Override
    public void afterRender(Component component) {
        if (component instanceof FormComponent<?> fc && !fc.isValid()) {
            String error;
            if (fc.hasFeedbackMessage()) {
                FeedbackMessage first = fc.getFeedbackMessages().first();
                first.markRendered();
                error = first.getMessage().toString();

            } else {
                error = "Your input is invalid.";
            }
            fc.getResponse().write("*<span class=\"invalid-feedback\">" + error + "</span>");
            super.afterRender(component);
        }
    }

}
