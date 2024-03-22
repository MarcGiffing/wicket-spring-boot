package com.giffing.wicket.spring.boot.example.web.html.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;

public abstract class YesNoModal extends ModalDialog {

	public YesNoModal(String id) {
		super(id);
		YesNoPanel yesNoPanel = new YesNoPanel(CONTENT_ID){

			@Override
			protected void yesClicked(AjaxRequestTarget target) {
				YesNoModal.this.yesClicked(target);
			}

			@Override
			protected void noClicked(AjaxRequestTarget target) {
				YesNoModal.this.noClicked(target);
			}
			
		};
		setContent(yesNoPanel);
	}

	protected void noClicked(AjaxRequestTarget target) {
		close(target);
	}

	protected abstract void yesClicked(AjaxRequestTarget target);
	
}
