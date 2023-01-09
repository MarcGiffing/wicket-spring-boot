package com.giffing.wicket.spring.boot.example.web.general.action.panel.items.yesno;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstractActionItemLink;
import com.giffing.wicket.spring.boot.example.web.general.icons.IconType;
import com.giffing.wicket.spring.boot.example.web.html.modal.YesNoModal;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;

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
				this.close(target);
			}
		};
		((BasePage)getPage()).replaceDefaultModal(yesNoModal);
		yesNoModal.open(target);
	}

	protected abstract void yesClicked(AjaxRequestTarget target);
	
}
