package com.giffing.wicket.spring.boot.starter.app;

import org.apache.wicket.Page;

public class HomePageCandidate {

	private Class<Page> candidate;
	
	public HomePageCandidate(Class<Page> candidate) {
		this.setCandidate(candidate);
	}

	public Class<Page> getCandidate() {
		return candidate;
	}

	public void setCandidate(Class<Page> candidate) {
		this.candidate = candidate;
	}

}
