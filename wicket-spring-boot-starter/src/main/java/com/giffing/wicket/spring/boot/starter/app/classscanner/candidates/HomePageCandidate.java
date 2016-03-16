package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import org.apache.wicket.Page;

public class HomePageCandidate extends WicketClassCandidate<Page> {
	
	public HomePageCandidate(Class<Page> candidate) {
		super(candidate);
	}
	
}
