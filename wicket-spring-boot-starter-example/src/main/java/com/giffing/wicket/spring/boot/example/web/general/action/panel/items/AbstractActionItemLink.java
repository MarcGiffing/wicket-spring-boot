package com.giffing.wicket.spring.boot.example.web.general.action.panel.items;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.general.icons.IconType;

public abstract class AbstractActionItemLink<T> extends AbstrractActionItem {
	
	public AbstractActionItemLink(IModel<T> label, IconType iconType){
		AjaxLink<T> link = new AjaxLink<T>("link") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				AbstractActionItemLink.this.onClick(target);
				
			}
		};
		add(link);
		WebMarkupContainer webMarkupContainer = new WebMarkupContainer("icon-type");
		webMarkupContainer.add(new AttributeAppender("class", iconType.getCssName()));
		link.add(webMarkupContainer);
	}
	
	public abstract void onClick(AjaxRequestTarget target);
}
