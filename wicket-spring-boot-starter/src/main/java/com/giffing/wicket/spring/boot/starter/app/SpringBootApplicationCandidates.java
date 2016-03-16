package com.giffing.wicket.spring.boot.starter.app;

public class SpringBootApplicationCandidates {

	private Class<?> candidate;
	
	public SpringBootApplicationCandidates(Class<?> candidate) {
		this.candidate = candidate;
	}

	public Class<?> getCandidate() {
		return candidate;
	}

	public void setCandidate(Class<?> candidate) {
		this.candidate = candidate;
	}
	
}
