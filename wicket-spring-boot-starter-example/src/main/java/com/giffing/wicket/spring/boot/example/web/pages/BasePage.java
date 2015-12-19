package com.giffing.wicket.spring.boot.example.web.pages;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.EmptyPanel;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

public abstract class BasePage extends WebPage {

	private MarkupContainer defaultModal;
	
	public BasePage(){
		defaultModal = new EmptyPanel("defaultModal");
		defaultModal.setOutputMarkupId(true);
		add(defaultModal);
	}
	
	public void replaceDefaultModal(ModalWindow newModal){
		defaultModal.replaceWith(newModal);
		defaultModal = newModal;
		defaultModal.setOutputMarkupId(true);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getWicketEventReference()));
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getWicketAjaxReference()));
		
		response.render(JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference("bootstrap/current/js/bootstrap.js")));
		response.render(CssHeaderItem.forReference(new WebjarsJavaScriptResourceReference("bootstrap/current/css/bootstrap.css")));
	}

}
