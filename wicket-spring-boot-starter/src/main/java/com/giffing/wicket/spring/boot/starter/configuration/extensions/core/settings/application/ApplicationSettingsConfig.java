package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.application;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;
import com.giffing.wicket.spring.boot.context.scan.WicketExpiredPage;
import com.giffing.wicket.spring.boot.context.scan.WicketInternalErrorPage;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidate;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

@ApplicationInitExtension	
public class ApplicationSettingsConfig implements WicketApplicationInitConfiguration {
	
	@Autowired
	private WicketClassCandidatesHolder holder;
	

	@Override
	public void init(WebApplication webApplication) {
		ApplicationSettings applicationSettings = webApplication.getApplicationSettings();
		
		configureExpiredPage(applicationSettings, new ArrayList<>(holder.getExpiredPageCandidates()));
		configureAccessDeniedPage(applicationSettings, new ArrayList<>(holder.getAccessDeniedPageCandidates()));
		configureInternalErrorPage(applicationSettings, new ArrayList<>(holder.getInternalErrorPageCandidates()));
	}
	
	private void configureInternalErrorPage(ApplicationSettings applicationSettings, List<WicketClassCandidate<Page>> internalErrorPageCandidates) {
		if(internalErrorPageCandidates.size() > 0) {
			if(internalErrorPageCandidates.size() == 1){
				WicketClassCandidate<Page> internalErrorPage = internalErrorPageCandidates.get(0);
				applicationSettings.setInternalErrorPage(internalErrorPage.getCandidate());
			} else {
				throwExceptionOnMultipleAnnotations(WicketInternalErrorPage.class, internalErrorPageCandidates);
			}
		}
	}

	private void configureAccessDeniedPage(ApplicationSettings applicationSettings, List<WicketClassCandidate<Page>> accessDeniedPageCandidates) {
		if(accessDeniedPageCandidates.size() > 0) {
			if(accessDeniedPageCandidates.size() == 1){
				WicketClassCandidate<Page> accessDeniedPage = accessDeniedPageCandidates.get(0);
				applicationSettings.setAccessDeniedPage(accessDeniedPage.getCandidate());
			} else {
				throwExceptionOnMultipleAnnotations(WicketAccessDeniedPage.class, accessDeniedPageCandidates);
			}
		}
	}

	private void configureExpiredPage(ApplicationSettings applicationSettings, List<WicketClassCandidate<Page>> expiredPageCandidates) {
		if(expiredPageCandidates.size() > 0) {
			if(expiredPageCandidates.size() == 1){
				WicketClassCandidate<Page> expiredPageCandidate = expiredPageCandidates.get(0);
				applicationSettings.setPageExpiredErrorPage(expiredPageCandidate.getCandidate());
			} else {
				throwExceptionOnMultipleAnnotations(WicketExpiredPage.class, expiredPageCandidates);
			}
		}
	}

	private void throwExceptionOnMultipleAnnotations(Class<?> pageClass, List<WicketClassCandidate<Page>> expiredPageCandidates) throws IllegalAccessError {
		String message = "Multiple annotation of " + pageClass.getName() + " found";
		message += "\n";
		for(WicketClassCandidate<Page> classCandidate : expiredPageCandidates) {
			message += "\t" + classCandidate.getCandidate() + "\n";
		}
		throw new IllegalAccessError(message);
	}

}
