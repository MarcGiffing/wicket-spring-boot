package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import java.lang.ref.WeakReference;

/**
 * @author Marc Giffing
 *
 * @param <T> candidate class type
 */
public class WicketClassCandidate<T> {

	private WeakReference<Class<T>> candidate;
	
	public WicketClassCandidate(WeakReference<Class<T>> candidate) {
		this.candidate = candidate;
	}

	public WeakReference<Class<T>> getCandidate() {
		return candidate;
	}

	public void setCandidate(WeakReference<Class<T>> candidate) {
		this.candidate = candidate;
	}
	
}
