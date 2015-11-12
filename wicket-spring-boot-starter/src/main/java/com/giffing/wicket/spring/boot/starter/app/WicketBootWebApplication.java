package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.giffing.wicket.spring.boot.starter.WicketProperties;
import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.pages.HomePage;
import com.giffing.wicket.spring.boot.starter.pages.LoginPage;
import com.giffing.wicket.spring.boot.starter.security.SecureWebSession;

public class WicketBootWebApplication extends AuthenticatedWebApplication {

	private final static Logger logger = LoggerFactory
		.getLogger(WicketBootWebApplication.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private WicketProperties wicketProperties;

	@Autowired(required = false)
	private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

		getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));

		new AnnotatedMountScanner().scanPackage(this.getClass().getPackage().getName()).mount(this);

		for (WicketApplicationInitConfiguration configuration : configurations) {
			logger.info("init-config: " + configuration.getClass().getName());
			configuration.init(this);
		}

	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SecureWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return wicketProperties.getConfigurationType();
	}
}
