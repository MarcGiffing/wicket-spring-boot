package com.giffing.wicket.spring.boot.starter.app;

import java.util.ArrayList;
import java.util.List;

public class PageCandiates {

	private List<HomePageCandiate> homePageCandiates = new ArrayList<>();
	
	private List<SignInPageCandiate> signInPageCandiates = new ArrayList<>();
	
	public PageCandiates(List<HomePageCandiate> homePageCandiates, List<SignInPageCandiate> signInPageCandiates) {
		this.homePageCandiates = homePageCandiates;
		this.signInPageCandiates = signInPageCandiates;
	}
	
	public List<HomePageCandiate> getHomePageCandiates() {
		return homePageCandiates;
	}

	public void setHomePageCandiates(List<HomePageCandiate> homePageCandiates) {
		this.homePageCandiates = homePageCandiates;
	}

	public List<SignInPageCandiate> getSignInPageCandiates() {
		return signInPageCandiates;
	}

	public void setSignInPageCandiates(List<SignInPageCandiate> signInPageCandiates) {
		this.signInPageCandiates = signInPageCandiates;
	}
	
}
