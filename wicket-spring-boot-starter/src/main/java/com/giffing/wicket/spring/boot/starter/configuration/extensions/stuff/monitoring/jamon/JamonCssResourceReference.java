package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.monitoring.jamon;

import org.apache.wicket.request.resource.CssResourceReference;


public class JamonCssResourceReference extends CssResourceReference {

	/**
     * Singleton instance of this reference
     */
    public static final JamonCssResourceReference INSTANCE = new JamonCssResourceReference();

    /**
     * Private constructor.
     */
    private JamonCssResourceReference() {
        super(JamonCssResourceReference.class, "css/jamon.css");
    }
}
