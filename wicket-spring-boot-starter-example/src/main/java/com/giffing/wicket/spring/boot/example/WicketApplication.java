package com.giffing.wicket.spring.boot.example;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.giffing.wicket.spring.boot.example.repository.jpa.support.CustomSimpleJpaRepository;
import com.giffing.wicket.spring.boot.example.repository.services.DefaultRepositoryService;
import com.giffing.wicket.spring.boot.example.web.pages.home.HomePage;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses={DefaultRepositoryService.class}, repositoryBaseClass = CustomSimpleJpaRepository.class)
public class WicketApplication extends WicketBootSecuredWebApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder().sources(WicketApplication.class).run(args);
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
