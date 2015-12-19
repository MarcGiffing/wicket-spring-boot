package com.giffing.wicket.spring.boot.example.web.html.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

public abstract class YesNoModal extends ModalWindow {

	public YesNoModal(String id) {
		super(id);
		setMinimalHeight(200);
		setMinimalWidth(400);
		setInitialHeight(200);
		setInitialWidth(400);
		setAutoSize(false);
		String contentId = getContentId();
		YesNoPanel yesNoPanel = new YesNoPanel(contentId){

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
