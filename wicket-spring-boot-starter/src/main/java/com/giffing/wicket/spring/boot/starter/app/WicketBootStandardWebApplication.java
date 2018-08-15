package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
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
 * To initialize the application in an decoupled way a list of {@link WicketApplicationInitConfiguration}
 * is injected as a list in this class. Each configuration item will be called in the init method.
 * 
 * @author Marc Giffing
 *
 */
@Lazy
public class WicketBootStandardWebApplication extends WebApplication implements WicketBootWebApplication {

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
	@Autowired(required = false)
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
			logger.info("init-config: " + configuration.getClass().getName());
			configuration.init(this);
		}
		
	}
	
	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return generalSettingsProperties.getConfigurationType();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Page> getHomePage() {
		if(classCandidates.getHomePageCandidates().size() <= 0){
			throw new IllegalStateException("Couln't find home page - please annotate the home page with @" + WicketHomePage.class.getName());
		}
		if(classCandidates.getHomePageCandidates().size() > 1 ){
			String message = "Multiple home pages found - please annotate exactly one class with @" + WicketHomePage.class.getName();
			message += "\n";
			for(WicketClassCandidate<Page> classCandidate : classCandidates.getHomePageCandidates()) {
				message += "\t" + classCandidate.getCandidate() + "\n";
			}
			throw new IllegalStateException(message);
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

	public WicketClassCandidatesHolder getClassCandidates() {
		return classCandidates;
	}

	public void setClassCandidates(WicketClassCandidatesHolder classCandidates) {
		this.classCandidates = classCandidates;
	}

}
