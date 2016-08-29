package com.giffing.wicket.spring.boot.example.web.pages.dashboard.widgets;

import java.util.UUID;

import org.apache.wicket.model.Model;
import org.springframework.stereotype.Component;
import org.wicketstuff.dashboard.AbstractWidget;
import org.wicketstuff.dashboard.Widget;
import org.wicketstuff.dashboard.web.WidgetView;

	public class CustomWidget extends AbstractWidget {

	public CustomWidget() {
		super();
		setId(UUID.randomUUID().toString());
		setTitle(UUID.randomUUID().toString());
	}
	
	@Override
	public WidgetView createView(String viewId) {
		return new CustomWidgetView(viewId,  new Model<Widget>(this));
	}

}

