package com.giffing.wicket.spring.boot.example.web.general.action.panel.items.yesno;

import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstractActionItemLink;
import com.giffing.wicket.spring.boot.example.web.general.icons.IconType;
import com.giffing.wicket.spring.boot.example.web.html.modal.YesNoModal;

public abstract class YesNoLink<T> extends AbstractActionItemLink<T>{

	public YesNoLink(IModel<T> label, IconType iconType) {
		super(label, iconType);
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		YesNoModal yesNoModal = new YesNoModal("defaultModal"){

			@Override
			protected void yesClicked(AjaxRequestTarget target) {
				YesNoLink.this.yesClicked(target);
				close(target);
				((BasePage)getPage()).replaceDefaultModal( new EmptyPanel("defaultModal"), target);
			}
		};
		((BasePage)getPage()).replaceDefaultModal(yesNoModal, target);
		yesNoModal.appendShowDialogJavaScript(target);
	}

	protected abstract void yesClicked(AjaxRequestTarget target);
	
}
