package com.giffing.wicket.spring.boot.example.web.general.action.panel.items.links;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstrractActionItem;

public class ActionItemLink extends AbstrractActionItem {
	
	public ActionItemLink(IModel<String> label, IconType iconType, AbstractLink link) {
		add(link);
		link.add(new Icon("icon", iconType));
	}

}
