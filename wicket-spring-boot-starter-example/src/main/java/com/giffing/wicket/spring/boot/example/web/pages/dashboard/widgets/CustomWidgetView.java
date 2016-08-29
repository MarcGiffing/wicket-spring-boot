package com.giffing.wicket.spring.boot.example.web.pages.dashboard.widgets;

import org.apache.wicket.model.IModel;
import org.wicketstuff.dashboard.Widget;
import org.wicketstuff.dashboard.web.WidgetView;

public class CustomWidgetView extends WidgetView {

	public CustomWidgetView(String id, IModel<Widget> model) {
		super(id, model);
	}

}
