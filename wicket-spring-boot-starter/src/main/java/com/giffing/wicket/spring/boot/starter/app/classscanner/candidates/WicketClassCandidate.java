package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

/**
 * @author Marc Giffing
 *
 * @param <T> candidate class type
 */
public abstract class WicketClassCandidate<T> {

	private Class<T> candidate;
	
	public WicketClassCandidate(Class<T> candidate) {
		this.candidate = candidate;
	}

	public Class<T> getCandidate() {
		return candidate;
	}

	public void setCandidate(Class<T> candidate) {
		this.candidate = candidate;
	}
	
}
