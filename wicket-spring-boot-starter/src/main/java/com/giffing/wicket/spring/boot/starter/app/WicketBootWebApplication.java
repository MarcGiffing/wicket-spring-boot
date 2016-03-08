package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;
import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

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
public abstract class WicketBootWebApplication extends WebApplication {

	private final static Logger logger = LoggerFactory
		.getLogger(WicketBootWebApplication.class);

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private GeneralSettingsProperties generalSettingsProperties;
	
	@PostConstruct
	public void postConstruct() {
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
		beanFactory.registerSingleton(WicketWebInitializer.WICKET_SPRING_APPLICATION_BEAN_NAME, this);
	}
	
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
		
		for (WicketApplicationInitConfiguration configuration : configurations) {
			logger.info("init-config: " + configuration.getClass().getName());
			configuration.init(this);
		}
		
	}
	
	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return generalSettingsProperties.getConfigurationType();
	}

}
