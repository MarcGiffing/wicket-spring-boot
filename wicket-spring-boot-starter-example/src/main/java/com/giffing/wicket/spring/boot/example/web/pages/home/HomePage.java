package com.giffing.wicket.spring.boot.example.web.pages.home;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;

@MountPath("home")
@AuthorizeInstantiation("USER")
public class HomePage extends BasePage {

	public HomePage(){
		add(new Link<String>("customersLink") {

			@Override
			public void onClick() {
				setResponsePage(CustomerListPage.class);
				
			}
		});
	}
	
}
