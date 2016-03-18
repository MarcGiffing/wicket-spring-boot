package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;

/**
 * Holds class candidates which should be configures in Wicket.
 * It holds e.g. home page classes which should be configures in Wicket. 
 * 
 * @author Marc Giffing
 *
 */
public class WicketClassCandidatesHolder {
	
	private Set<String> basePackages = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> homePageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<WebPage>> signInPageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> accessDeniedPageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> expiredPageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> internalErrorPageCandidates = new HashSet<>();

	public Set<WicketClassCandidate<Page>> getHomePageCandidates() {
		return homePageCandidates;
	}

	public void setHomePageCandidates(Set<WicketClassCandidate<Page>> homePageCandidates) {
		this.homePageCandidates = homePageCandidates;
	}

	public Set<WicketClassCandidate<WebPage>> getSignInPageCandidates() {
		return signInPageCandidates;
	}

	public void setSignInPageCandidates(Set<WicketClassCandidate<WebPage>> signInPageCandidates) {
		this.signInPageCandidates = signInPageCandidates;
	}

	public Set<WicketClassCandidate<Page>> getAccessDeniedPageCandidates() {
		return accessDeniedPageCandidates;
	}

	public void setAccessDeniedPageCandidates(Set<WicketClassCandidate<Page>> accessDeniedPageCandidates) {
		this.accessDeniedPageCandidates = accessDeniedPageCandidates;
	}

	public Set<WicketClassCandidate<Page>> getExpiredPageCandidates() {
		return expiredPageCandidates;
	}

	public void setExpiredPageCandidates(Set<WicketClassCandidate<Page>> expiredPageCandidates) {
		this.expiredPageCandidates = expiredPageCandidates;
	}

	public Set<WicketClassCandidate<Page>> getInternalErrorPageCandidates() {
		return internalErrorPageCandidates;
	}

	public void setInternalErrorPageCandidates(Set<WicketClassCandidate<Page>> internalErrorPageCandidates) {
		this.internalErrorPageCandidates = internalErrorPageCandidates;
	}

	public Set<String> getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(Set<String> basePackages) {
		this.basePackages = basePackages;
	}

}
