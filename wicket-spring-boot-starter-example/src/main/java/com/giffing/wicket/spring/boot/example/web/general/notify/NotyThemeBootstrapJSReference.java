package com.giffing.wicket.spring.boot.example.web.general.notify;

import org.apache.wicket.resource.JQueryPluginResourceReference;

public class NotyThemeBootstrapJSReference extends JQueryPluginResourceReference {

	public static final NotyThemeBootstrapJSReference INSTANCE = new NotyThemeBootstrapJSReference();

	public NotyThemeBootstrapJSReference() {
		super(NotyThemeBootstrapJSReference.class, "noty.theme.bootstrap.js");
	}

}
