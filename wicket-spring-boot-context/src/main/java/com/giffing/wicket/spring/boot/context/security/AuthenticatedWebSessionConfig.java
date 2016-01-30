package com.giffing.wicket.spring.boot.context.security;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;

public interface AuthenticatedWebSessionConfig {

	Class<? extends AbstractAuthenticatedWebSession> getAuthenticatedWebSessionClass();
	
}
