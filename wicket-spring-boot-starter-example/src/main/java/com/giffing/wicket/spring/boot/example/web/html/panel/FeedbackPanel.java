package com.giffing.wicket.spring.boot.example.web.html.panel;

import org.apache.wicket.feedback.IFeedbackMessageFilter;

public class FeedbackPanel extends org.apache.wicket.markup.html.panel.FeedbackPanel {

	public FeedbackPanel(String string) {
		super(string);
	}

	public FeedbackPanel(String id, IFeedbackMessageFilter filter) {
		super(id, filter);
	}

}
