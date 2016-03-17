package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import java.util.ArrayList;
import java.util.List;

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
	
	private List<WicketClassCandidate<Page>> homePageCandidates = new ArrayList<>();
	
	private List<WicketClassCandidate<WebPage>> signInPageCandidates = new ArrayList<>();

	private List<WicketClassCandidate> springBootApplicationCandidates;
	
	
	public WicketClassCandidatesHolder(List<WicketClassCandidate<Page>> homePageCandidates, 
			List<WicketClassCandidate<WebPage>> signInPageCandidates, 
			List<WicketClassCandidate> springBootApplicationCandidates) {
		
		this.homePageCandidates = homePageCandidates;
		this.signInPageCandidates = signInPageCandidates;
		this.springBootApplicationCandidates = springBootApplicationCandidates;
	}
	
	public List<WicketClassCandidate<Page>> getHomePageCandidates() {
		return homePageCandidates;
	}

	public void setHomePageCandidates(List<WicketClassCandidate<Page>> homePageCandidates) {
		this.homePageCandidates = homePageCandidates;
	}

	public List<WicketClassCandidate<WebPage>> getSignInPageCandidates() {
		return signInPageCandidates;
	}

	public void setSignInPageCandidates(List<WicketClassCandidate<WebPage>> signInPageCandidates) {
		this.signInPageCandidates = signInPageCandidates;
	}

	public List<WicketClassCandidate> getSpringBootApplicationCandidates() {
		return springBootApplicationCandidates;
	}

	public void setSpringBootApplicationCandidates(List<WicketClassCandidate> springBootApplicationCandidates) {
		this.springBootApplicationCandidates = springBootApplicationCandidates;
	}

}
