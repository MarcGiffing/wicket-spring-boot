package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.csrf;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.protocol.http.ResourceIsolationRequestCycleListener.CsrfAction;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CsrfAttacksPreventionProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class CsrfAttacksPreventionProperties {
	
	public static final String PROPERTY_PREFIX = "wicket.core.csrf";
	
	/**
	 * Action to perform when a request is disallowed by a resource isolation policy.
	 * Default is {@link CsrfAction#ABORT}.
	 */
	private CsrfAction disallowedOutcomeAction = CsrfAction.ABORT;

	/**
	 * Action to perform when none of the resource isolation policies can come to an outcome.
	 * Default is {@link CsrfAction#ABORT}.
	 */
	private CsrfAction unknownOutcomeAction = CsrfAction.ABORT;

	/**
	 * The error code to report when the action to take for a CSRF request is
	 * {@link CsrfAction#ABORT}. Default {@code 400 BAD REQUEST}.
	 */
	private int errorCode = jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

	/**
	 * The error message to report when the action to take for a CSRF request is {@code ERROR}.
	 * Default {@code "Origin does not correspond to request"}.
	 */
	private String errorMessage = "Origin does not correspond to request";

	/**
	 * A white list of accepted origins (host names/domain names) presented as
	 * &lt;domainname&gt;.&lt;TLD&gt;. The domain part can contain subdomains.
	 */
	private List<String> acceptedOrigins = new ArrayList<>();
	
	/**
	 * Enables Wickets CSRF protection
	 */
	private boolean enabled = true;

}
