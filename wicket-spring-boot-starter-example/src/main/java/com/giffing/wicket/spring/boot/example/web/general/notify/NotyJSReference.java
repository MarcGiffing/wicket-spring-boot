package com.giffing.wicket.spring.boot.example.web.general.notify;

import org.apache.wicket.resource.JQueryPluginResourceReference;

public class NotyJSReference extends JQueryPluginResourceReference {

    public static final NotyJSReference INSTANCE = new NotyJSReference();

    public NotyJSReference() {
        super(NotyJSReference.class, "jquery.noty.js");
    }

}