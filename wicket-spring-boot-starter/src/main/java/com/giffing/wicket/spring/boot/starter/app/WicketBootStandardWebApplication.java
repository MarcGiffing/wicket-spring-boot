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

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
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
public class WicketBootStandardWebApplication extends WebApplication implements WicketBootWebApplication {

	private final static Logger logger = LoggerFactory
		.getLogger(WicketBootStandardWebApplication.class);

	private ApplicationContext applicationContext;
	
	private GeneralSettingsProperties generalSettingsProperties;
	
	/**
	 * Injects all active extension which matches the predefined conditions. May be empty
	 * if no extension matches the given preconditions.
	 */
	@Autowired(required = false)
	private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();
	
	@Autowired
	private PageCandiates pageCandiates;
	
	@Autowired
	public WicketBootStandardWebApplication(ApplicationContext applicationContext, List<WicketApplicationInitConfiguration> configurations, GeneralSettingsProperties generalSettingsProperties) {
		this.applicationContext = applicationContext;
		this.configurations = configurations;
		this.generalSettingsProperties = generalSettingsProperties;
	}
	
	
	@Override
	protected void init() {
		super.init();
	    
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
		
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
		if(pageCandiates.getHomePageCandiates().size() != 1){
			throw new IllegalStateException("Couln't find home page - please annotated the home page with @Home");
		}
		
		Class<Page> next = pageCandiates.getHomePageCandiates().get(0).getCandiate();
		return next;
	}

}
