package com.giffing.wicket.spring.boot.example.web.html.modal;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class YesNoPanel extends Panel{
	
	
	public YesNoPanel(String id) {
		super(id);
		
		add(new AjaxLink<Void>("yes") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				YesNoPanel.this.yesClicked(target);
			}

			
		});
		
		add(new AjaxLink<Void>("no") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				YesNoPanel.this.noClicked(target);
			}
			
			
		});
		
	}

	protected abstract void noClicked(AjaxRequestTarget target);

	protected abstract void yesClicked(AjaxRequestTarget target);
	

}
