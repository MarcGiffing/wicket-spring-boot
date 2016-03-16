package com.giffing.wicket.spring.boot.starter.app;

import org.apache.wicket.markup.html.WebPage;

public class SignInPageCandidates {

	private Class<WebPage> candidate;
	
	public SignInPageCandidates(Class<WebPage> candidate) {
		this.setCandidate(candidate);
	}

	public Class<WebPage> getCandidate() {
		return candidate;
	}

	public void setCandidate(Class<WebPage> candidate) {
		this.candidate = candidate;
	}

}
