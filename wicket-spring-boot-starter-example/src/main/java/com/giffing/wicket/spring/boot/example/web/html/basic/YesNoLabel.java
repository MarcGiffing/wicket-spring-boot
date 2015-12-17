package com.giffing.wicket.spring.boot.example.web.html.basic;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class YesNoLabel extends Label {

	public YesNoLabel(String id, IModel<Boolean> model) {
		super(id, model);
	}

	@Override
	public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		boolean active = (boolean)getDefaultModelObject();
		String resourceKey = active ? "yes" : "no";
		replaceComponentTagBody(markupStream, openTag, getString(resourceKey));
	}

	

}
