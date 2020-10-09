package com.giffing.wicket.spring.boot.example.web.general.notify;

import org.apache.wicket.resource.JQueryPluginResourceReference;

public class NotyPackagedJSReference extends JQueryPluginResourceReference {

	public static final NotyPackagedJSReference INSTANCE = new NotyPackagedJSReference();

	public NotyPackagedJSReference() {
		super(NotyPackagedJSReference.class, "jquery.noty.packaged.min.js");
	}

}
