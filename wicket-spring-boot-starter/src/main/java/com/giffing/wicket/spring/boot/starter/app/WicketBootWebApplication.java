package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.WebSecurityConfig;
import com.giffing.wicket.spring.boot.starter.WicketProperties;
import com.giffing.wicket.spring.boot.starter.pages.HomePage;
import com.giffing.wicket.spring.boot.starter.pages.LoginPage;
import com.giffing.wicket.spring.boot.starter.security.SecureWebSession;

/**
 * Default Wicket Application which should be subclassed by framework clients to
 * enable autoconfiguration with help of Spring Boot.
 * 
 * It automatically configures the {@link SpringComponentInjector} to 
 * enable injection of Spring components via the {@link SpringBean} annotation.
 * 
 * Beside Springs injection support it also automatically enables Spring Security
 * support with a default {@link AbstractAuthenticatedWebSession} implementation 
 * {@link SecureWebSession}. It uses and {@link AuthenticationManager} in 
 * {@link WebSecurityConfig} which can be overridden by defining an 
 * own {@link WebSecurityConfigurerAdapter} bean.
 * 
 * To initialize the application in an decoupled way a list of {@link WicketApplicationInitConfiguration}
 * is injected as a list in this class. Each configuration item will be called in the init method.
 * 
 * @author Marc Giffing
 *
 */
public class WicketBootWebApplication extends AuthenticatedWebApplication {

	private final static Logger logger = LoggerFactory
		.getLogger(WicketBootWebApplication.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private WicketProperties wicketProperties;
	
	/**
	 * Injects all active extension which matches the predefined conditions. May be empty
	 * if no precondition matches the given preconditions.
	 */
	@Autowired(required = false)
	private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();
	
	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
		getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
		
		for (WicketApplicationInitConfiguration configuration : configurations) {
			logger.info("init-config: " + configuration.getClass().getName());
			configuration.init(this);
		}

	}
	
	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return wicketProperties.getConfigurationType();
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SecureWebSession.class;
	}
	
	/**
	 * The default home page which can be overridden.
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	
}
