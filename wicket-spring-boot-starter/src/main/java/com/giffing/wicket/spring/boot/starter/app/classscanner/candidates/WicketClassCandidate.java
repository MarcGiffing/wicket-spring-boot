package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import java.lang.ref.WeakReference;

/**
 * @author Marc Giffing
 *
 * @param <T> candidate class type
 */
public class WicketClassCandidate<T> {

	private WeakReference<Class<T>> candidate;
	
	public WicketClassCandidate(Class<T> candidate) {
		this.candidate = new WeakReference<Class<T>>(candidate);
	}

	public Class<T> getCandidate() {
		return candidate.get();
	}

	
}
