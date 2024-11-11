package com.giffing.wicket.spring.boot.example.web.assets.base;

import org.apache.wicket.request.resource.CssResourceReference;

public class FixBootstrapStylesCssResourceReference extends CssResourceReference {

    public static final FixBootstrapStylesCssResourceReference INSTANCE = new FixBootstrapStylesCssResourceReference();

    public FixBootstrapStylesCssResourceReference() {
        super(FixBootstrapStylesCssResourceReference.class, "fix.css");
    }
}
