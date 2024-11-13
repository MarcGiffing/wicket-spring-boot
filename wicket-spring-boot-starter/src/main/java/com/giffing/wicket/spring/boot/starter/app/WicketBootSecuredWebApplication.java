package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidate;
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
@RequiredArgsConstructor
@Slf4j
public class WicketBootSecuredWebApplication extends AuthenticatedWebApplication implements WicketBootWebApplication {

	@Autowired
	@Getter
	private ApplicationContext applicationContext;
	
	@Autowired
	@Getter
	private GeneralSettingsProperties generalSettingsProperties;

	/**
	 * Injects all active extension which matches the predefined conditions. May be empty
	 * if no extension matches the given preconditions.
	 */
	@Autowired
	@Getter
	private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();
	
	@Autowired
	private WicketClassCandidatesHolder classCandidates;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	protected void init() {
		super.init();
	    
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
		
		WicketAutoConfig.Builder builder = new WicketAutoConfig.Builder(this.getClass());
		wicketEndpointRepository.add(builder
				.withDetail("signInPages", classCandidates.getSignInPageCandidates())
				.withDetail("homePages", classCandidates.getHomePageCandidates())
				.build());
		
		for (WicketApplicationInitConfiguration configuration : configurations) {
			log.info("init-config: {}", configuration.getClass().getName());
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
		if(classCandidates.getSignInPageCandidates().isEmpty()){
			throw new IllegalStateException("Couldn't find sign in page - please annotate the sign in page with @" + WicketSignInPage.class.getName());
		}
		if(classCandidates.getSignInPageCandidates().size() > 1 ){
			String message = "Multiple sign in pages found - please annotate exactly one class with @" + WicketSignInPage.class.getName();
			message += "\n";
			for (WicketClassCandidate<WebPage> classCandidate : classCandidates.getSignInPageCandidates()) {
				message += "\t" + classCandidate.getCandidate() + "\n";
			}
			throw new IllegalStateException(message);
		}

		return classCandidates.getSignInPageCandidates().iterator().next().getCandidate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Page> getHomePage() {
		if(classCandidates.getHomePageCandidates().isEmpty()){
			throw new IllegalStateException("Couldn't find home page - please annotate the home page with @" + WicketHomePage.class.getName());
		}
		if(classCandidates.getHomePageCandidates().size() > 1 ){
			String message = "Multiple home pages found - please annotate exactly one class with @" + WicketHomePage.class.getName();
			message += "\n";
			for(WicketClassCandidate<Page> classCandidate : classCandidates.getHomePageCandidates()) {
				message += "\t" + classCandidate.getCandidate() + "\n";
			}
			throw new IllegalStateException(message);
		}

		return classCandidates.getHomePageCandidates().iterator().next().getCandidate();
	}

}
