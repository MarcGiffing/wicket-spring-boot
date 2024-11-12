package com.giffing.wicket.spring.boot.example.web.assets.base;

import org.apache.wicket.request.resource.CssResourceReference;

public class CustomStylesCssRessourceReference extends CssResourceReference {

    public static final CustomStylesCssRessourceReference INSTANCE = new CustomStylesCssRessourceReference();

    public CustomStylesCssRessourceReference() {
        super(CustomStylesCssRessourceReference.class, "custom.css");
    }
}
