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

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;
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
public class WicketBootSecuredWebApplication extends AuthenticatedWebApplication implements WicketBootWebApplication {
	
	private final static Logger logger = LoggerFactory
		.getLogger(WicketBootStandardWebApplication.class);

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private GeneralSettingsProperties generalSettingsProperties;

	/**
	 * Injects all active extension which matches the predefined conditions. May be empty
	 * if no extension matches the given preconditions.
	 */
	@Autowired
	private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();
	
	@Autowired
	private WicketClassCandidatesHolder classCandidates;
	
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

	@SuppressWarnings("unchecked")
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		if(classCandidates.getSignInPageCandidates().size() != 1){
			throw new IllegalStateException("Couln't find home page - please annotated the home page with @" + WicketSignInPage.class.getName());
		}
		
		Class<WebPage> candidateClass = classCandidates.getSignInPageCandidates().iterator().next().getCandidate();
		return candidateClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Page> getHomePage() {
		if(classCandidates.getHomePageCandidates().size() != 1){
			throw new IllegalStateException("Couln't find home page - please annotated the home page with @" + WicketHomePage.class.getName());
		}
		
		Class<Page> next = classCandidates.getHomePageCandidates().iterator().next().getCandidate();
		return next;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public GeneralSettingsProperties getGeneralSettingsProperties() {
		return generalSettingsProperties;
	}

	public void setGeneralSettingsProperties(GeneralSettingsProperties generalSettingsProperties) {
		this.generalSettingsProperties = generalSettingsProperties;
	}

	public List<WicketApplicationInitConfiguration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<WicketApplicationInitConfiguration> configurations) {
		this.configurations = configurations;
	}
	
}
