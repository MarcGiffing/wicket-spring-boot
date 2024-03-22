package com.giffing.wicket.spring.boot.example.web.pages;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.giffing.wicket.spring.boot.example.web.general.notify.NotyJSReference;
import com.giffing.wicket.spring.boot.example.web.general.notify.NotyPackagedJSReference;
import com.giffing.wicket.spring.boot.example.web.general.notify.NotyThemeBootstrapJSReference;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

public abstract class BasePage extends WebPage {

	private MarkupContainer defaultModal;
	
	public BasePage(PageParameters params){
		super(params);
		initPage();
	}
	
	public BasePage(){
		initPage();
	}
	
	private void initPage(){
		defaultModal = new EmptyPanel("defaultModal");
		defaultModal.setOutputMarkupId(true);
		add(defaultModal);
	}
	
	public void replaceDefaultModal(ModalDialog newModal){
		defaultModal.replaceWith(newModal);
		defaultModal = newModal;
		defaultModal.setOutputMarkupId(true);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getWicketAjaxReference()));
		response.render(JavaScriptHeaderItem.forReference(NotyJSReference.INSTANCE));
		response.render(JavaScriptHeaderItem.forReference(NotyPackagedJSReference.INSTANCE));
		response.render(JavaScriptHeaderItem.forReference(NotyThemeBootstrapJSReference.INSTANCE));
		
		String bootstrapPrefixPath = "bootstrap/current";
		response.render(JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference(bootstrapPrefixPath + "/js/bootstrap.js")));
		response.render(CssHeaderItem.forReference(new WebjarsJavaScriptResourceReference(bootstrapPrefixPath + "/css/bootstrap.css")));
	}

}
