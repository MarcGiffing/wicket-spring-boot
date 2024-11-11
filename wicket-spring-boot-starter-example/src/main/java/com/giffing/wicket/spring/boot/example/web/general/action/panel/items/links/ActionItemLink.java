package com.giffing.wicket.spring.boot.example.web.general.action.panel.items.links;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstrractActionItem;
import com.giffing.wicket.spring.boot.example.web.general.icons.IconType;

public class ActionItemLink extends AbstrractActionItem {
	
	public ActionItemLink(IModel<String> label, IconType iconType, AbstractLink link) {
		add(link);
		WebMarkupContainer webMarkupContainer = new WebMarkupContainer("icon-type");
		webMarkupContainer.add(new AttributeAppender("class", "fa-solid fa-" + iconType.getCssName()));
		link.add(webMarkupContainer);
	}

}
