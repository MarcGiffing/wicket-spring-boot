package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;

public abstract class WicketBootSecuredWebApplication extends AuthenticatedWebApplication {

	private final static Logger logger = LoggerFactory
		.getLogger(WicketBootWebApplication.class);

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private GeneralSettingsProperties generalSettingsProperties;
	
	/**
	 * Injects all active extension which matches the predefined conditions. May be empty
	 * if no extension matches the given preconditions.
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
		return generalSettingsProperties.getConfigurationType();
	}
	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return applicationContext.getBean(AuthenticatedWebSessionConfig.class).getAuthenticatedWebSessionClass();
	}

}
