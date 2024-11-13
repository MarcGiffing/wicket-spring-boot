package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import java.lang.ref.WeakReference;

/**
 * @param <T> candidate class type
 * @author Marc Giffing
 */
public class WicketClassCandidate<T> {

    private WeakReference<Class<T>> candidate;

    public WicketClassCandidate(Class<T> candidate) {
        this.candidate = new WeakReference<>(candidate);
    }

    public Class<T> getCandidate() {
        return candidate.get();
    }


}
