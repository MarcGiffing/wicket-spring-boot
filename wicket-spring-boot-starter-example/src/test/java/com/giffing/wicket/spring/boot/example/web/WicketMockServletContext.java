package com.giffing.wicket.spring.boot.example.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.SessionTrackingMode;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.mock.MockServletContext;

public class WicketMockServletContext extends MockServletContext {

	public WicketMockServletContext(Application application, String path) {
		super( application, path );
	}
	
	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		return new HashSet<>(Arrays.asList( SessionTrackingMode.COOKIE ));
	}

	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		return new HashSet<>(Arrays.asList( SessionTrackingMode.COOKIE ));
	}

}
