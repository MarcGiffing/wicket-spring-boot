package com.giffing.wicket.spring.boot.example.web.pages;

import org.apache.wicket.feedback.DefaultCleanupFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.web.html.border.BaseBorder;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;

@MountPath("first-page")
public class FirstPage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	public class MyModel implements IClusterable{
		public String text;
		public String text2;
		public String text3;
	}
	
	public FirstPage() {
		queue(new FeedbackPanel("feedback", new DefaultCleanupFeedbackMessageFilter()));
		queue(new ValidationForm<>("form", new CompoundPropertyModel<>(new MyModel())));
		queue(new RequiredTextField<String>("text"));
		queue(new RequiredTextField<String>("text2").add(StringValidator.exactLength(5)));
		queue(new BaseBorder<String>(getString("text3"), new RequiredTextField<String>("text3")));
		queue(new BookmarkablePageLink<String>("secondPage", SecondPage.class));
	}



}
