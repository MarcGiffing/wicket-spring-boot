package com.giffing.wicket.spring.boot.example.web;

import java.util.Set;

import jakarta.servlet.SessionTrackingMode;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.mock.MockServletContext;

public class WicketMockServletContext extends MockServletContext {

	public WicketMockServletContext(Application application, String path) {
		super( application, path );
	}
	
	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		return Set.of(SessionTrackingMode.COOKIE);
	}

	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		return Set.of(SessionTrackingMode.COOKIE);
	}

}
