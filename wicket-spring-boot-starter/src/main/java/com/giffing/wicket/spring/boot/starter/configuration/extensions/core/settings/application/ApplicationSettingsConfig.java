package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;
import com.giffing.wicket.spring.boot.context.scan.WicketExpiredPage;
import com.giffing.wicket.spring.boot.context.scan.WicketInternalErrorPage;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidate;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;


public class ApplicationSettingsConfig implements WicketApplicationInitConfiguration {
	
	@Autowired
	private WicketClassCandidatesHolder holder;
	
	@Override
	public void init(WebApplication webApplication) {
		ApplicationSettings applicationSettings = webApplication.getApplicationSettings();
		
		List<WicketClassCandidate<Page>> accessDeniedPageCandidates = new ArrayList<>();
		
		List<WicketClassCandidate<Page>> expiredPageCandidates = new ArrayList<>();
		
		List<WicketClassCandidate<Page>> internalErrorPageCandidates = new ArrayList<>();
		
		ClassPathScanningCandidateComponentProvider scanner =
	            new ClassPathScanningCandidateComponentProvider(false);
	    scanner.addIncludeFilter(new AnnotationTypeFilter(WicketAccessDeniedPage.class));
	    scanner.addIncludeFilter(new AnnotationTypeFilter(WicketExpiredPage.class));
	    scanner.addIncludeFilter(new AnnotationTypeFilter(WicketInternalErrorPage.class));
	    Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(holder.getSpringBootApplicationCandidates().get(0).getCandidate().getPackage().getName());
		for (BeanDefinition beanDefinition : beanDefinitions) {
			Class<?> beanClass;
			try {
				beanClass = Class.forName(beanDefinition.getBeanClassName());
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException(e);
			}
			if(beanClass.isAnnotationPresent(WicketAccessDeniedPage.class)){
				accessDeniedPageCandidates.add(new WicketClassCandidate<Page>((Class<Page>) beanClass));
			}
			if(beanClass.isAnnotationPresent(WicketExpiredPage.class)){
				expiredPageCandidates.add(new WicketClassCandidate<Page>((Class<Page>) beanClass));
			}
			if(beanClass.isAnnotationPresent(WicketInternalErrorPage.class)){
				internalErrorPageCandidates.add(new WicketClassCandidate<Page>((Class<Page>) beanClass));
			}
		}
	    
		configureExpiredPage(applicationSettings, expiredPageCandidates);
		configureAccessDeniedPage(applicationSettings, accessDeniedPageCandidates);
		configureInternalErrorPage(applicationSettings, internalErrorPageCandidates);
	}

	private void configureInternalErrorPage(ApplicationSettings applicationSettings, List<WicketClassCandidate<Page>> internalErrorPageCandidates) {
		if(internalErrorPageCandidates.size() > 0) {
			if(internalErrorPageCandidates.size() == 1){
				WicketClassCandidate<Page> internalErrorPage = internalErrorPageCandidates.get(0);
				applicationSettings.setInternalErrorPage(internalErrorPage.getCandidate());
			} else {
				throw new IllegalStateException("Multiple annotation of " + WicketInternalErrorPage.class.getName() + " found");
			}
		}
	}

	private void configureAccessDeniedPage(ApplicationSettings applicationSettings, List<WicketClassCandidate<Page>> accessDeniedPageCandidates) {
		if(accessDeniedPageCandidates.size() > 0) {
			if(accessDeniedPageCandidates.size() == 1){
				WicketClassCandidate<Page> accessDeniedPage = accessDeniedPageCandidates.get(0);
				applicationSettings.setAccessDeniedPage(accessDeniedPage.getCandidate());
			} else {
				throw new IllegalStateException("Multiple annotation of " + WicketAccessDeniedPage.class.getName() + " found");
			}
		}
	}

	private void configureExpiredPage(ApplicationSettings applicationSettings, List<WicketClassCandidate<Page>> expiredPageCandidates) {
		if(expiredPageCandidates.size() > 0) {
			if(expiredPageCandidates.size() == 1){
				WicketClassCandidate<Page> expiredPageCandidate = expiredPageCandidates.get(0);
				applicationSettings.setPageExpiredErrorPage(expiredPageCandidate.getCandidate());
			} else {
				throw new IllegalStateException("Multiple annotation of " + WicketExpiredPage.class.getName() + " found");
			}
		}
	}

}
