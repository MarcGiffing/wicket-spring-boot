package com.giffing.wicket.spring.boot.context.security;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;

/**
 * Enables the dynamic configuration of the {@link AbstractAuthenticatedWebSession} for the
 * {@link AuthenticatedWebApplication}. E.g. if spring security is present it provides a default WebSession
 * configuration.
 * <p>
 * Security providers should provide different implementations.
 *
 * @author Marc Giffing
 */
public interface AuthenticatedWebSessionConfig {

    Class<? extends AbstractAuthenticatedWebSession> getAuthenticatedWebSessionClass();

}
