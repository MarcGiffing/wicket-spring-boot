package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.csrf;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.protocol.http.CsrfPreventionRequestCycleListener.CsrfAction;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CsrfAttacksPreventionProperties.PROPERTY_PREFIX)
public class CsrfAttacksPreventionProperties {
	
	public static final String PROPERTY_PREFIX = "wicket.core.csrf";
	
	/**
	 * Action to perform when no Origin header is present in the request.
	 */
	private CsrfAction noOriginAction = CsrfAction.ALLOW;

	/**
	 * Action to perform when a conflicting Origin header is found.
	 */
	private CsrfAction conflictingOriginAction = CsrfAction.ABORT;

	/**
	 * The error code to report when the action to take for a CSRF request is
	 * {@link CsrfAction#ABORT}. Default {@code 400 BAD REQUEST}.
	 */
	private int errorCode = javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public CsrfAction getNoOriginAction() {
		return noOriginAction;
	}

	public void setNoOriginAction(CsrfAction noOriginAction) {
		this.noOriginAction = noOriginAction;
	}

	public CsrfAction getConflictingOriginAction() {
		return conflictingOriginAction;
	}

	public void setConflictingOriginAction(CsrfAction conflictingOriginAction) {
		this.conflictingOriginAction = conflictingOriginAction;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getAcceptedOrigins() {
		return acceptedOrigins;
	}

	public void setAcceptedOrigins(List<String> acceptedOrigins) {
		this.acceptedOrigins = acceptedOrigins;
	}

}
