package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;

/**
 * Default Wicket Application which should be subclassed by framework clients to
 * enable autoconfiguration with help of Spring Boot.
 * 
 * It automatically configures the {@link SpringComponentInjector} to 
 * enable injection of Spring components via the {@link SpringBean} annotation.
 * 
 * Beside Springs injection support it also automatically enables security support.
 * You also have to add a security provider like Spring Security (@link SpringSecurityConfig)
 * or Apache shiro. 
 * 
 * To initialize the application in an decoupled way a list of {@link WicketApplicationInitConfiguration}
 * is injected as a list in this class. Each configuration item will be called in the init method.
 * 
 * @author Marc Giffing
 *
 */
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
