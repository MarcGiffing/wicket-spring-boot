package com.giffing.wicket.spring.boot.starter.pages;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

/**
 * The default home page which is called after the user is successfully logged
 * in. Can be overridden in the {@link WicketBootWebApplication}
 * 
 * @author Marc Giffing
 *
 */
@AuthorizeInstantiation("ROLE_USER")
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public HomePage() {
		add(new Label("message", Model.of("Huhu")));
		
		add(new AjaxLink<String>("logout") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getSession().invalidate();
				setResponsePage(HomePage.class);
			}
		
			
		});
	}
}
