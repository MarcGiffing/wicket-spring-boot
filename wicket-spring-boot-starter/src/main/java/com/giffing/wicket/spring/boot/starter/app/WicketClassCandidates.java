package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

public class WicketClassCandidates {

	private List<HomePageCandidate> homePageCandidates = new ArrayList<>();
	
	private List<SignInPageCandidates> signInPageCandidates = new ArrayList<>();

	private List<SpringBootApplicationCandidates> springBootApplicationCandidates;
	
	public WicketClassCandidates(List<HomePageCandidate> homePageCandidates, 
			List<SignInPageCandidates> signInPageCandidates, 
			List<SpringBootApplicationCandidates> springBootApplicationCandidates) {
		
		this.homePageCandidates = homePageCandidates;
		this.signInPageCandidates = signInPageCandidates;
		this.setSpringBootApplicationCandidates(springBootApplicationCandidates);
	}
	
	public List<HomePageCandidate> getHomePageCandidates() {
		return homePageCandidates;
	}

	public void setHomePageCandidates(List<HomePageCandidate> homePageCandidates) {
		this.homePageCandidates = homePageCandidates;
	}

	public List<SignInPageCandidates> getSignInPageCandidates() {
		return signInPageCandidates;
	}

	public void setSignInPageCandidates(List<SignInPageCandidates> signInPageCandidates) {
		this.signInPageCandidates = signInPageCandidates;
	}

	public List<SpringBootApplicationCandidates> getSpringBootApplicationCandidates() {
		return springBootApplicationCandidates;
	}

	public void setSpringBootApplicationCandidates(List<SpringBootApplicationCandidates> springBootApplicationCandidates) {
		this.springBootApplicationCandidates = springBootApplicationCandidates;
	}
	
}
