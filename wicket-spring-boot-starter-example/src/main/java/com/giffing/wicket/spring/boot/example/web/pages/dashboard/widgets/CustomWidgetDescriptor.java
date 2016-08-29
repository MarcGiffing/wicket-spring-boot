package com.giffing.wicket.spring.boot.example.web.pages.dashboard.widgets;

import org.springframework.stereotype.Component;
import org.wicketstuff.dashboard.WidgetDescriptor;
import org.wicketstuff.dashboard.widgets.loremipsum.LoremIpsumWidget;

@Component
public class CustomWidgetDescriptor implements WidgetDescriptor {

	public String getDescription() {
		return "A simple custom widget.";
	}

	public String getName() {
		return "Custom";
	}

	public String getProvider() {
		return "The provider";
	}

	public String getWidgetClassName() {
		return CustomWidget.class.getName();
	}
	
	@Override
	public String getTypeName() {
		return "widget.custom";
	}

}
