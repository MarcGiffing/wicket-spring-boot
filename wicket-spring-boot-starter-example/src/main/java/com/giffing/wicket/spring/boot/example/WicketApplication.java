package com.giffing.wicket.spring.boot.example;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

@WicketSpringBootApplication
public class WicketApplication extends WicketBootWebApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder().sources(WicketApplication.class).run(args);
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return CustomerListPage.class;
	}

}
