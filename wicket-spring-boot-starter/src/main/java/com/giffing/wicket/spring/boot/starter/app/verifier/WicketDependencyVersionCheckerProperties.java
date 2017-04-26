package com.giffing.wicket.spring.boot.starter.app.verifier;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = WicketDependencyVersionCheckerProperties.PROPERTY_PREFIX)
public class WicketDependencyVersionCheckerProperties {
	
	public static final String PROPERTY_PREFIX = "wicket.verifier.dependencies";
	
	private boolean enabled = true;
	
	private boolean throwExceptionOnDependencyVersionMismatch = true;

	
	public boolean isEnabled() {
		return enabled;
	}

	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public boolean isThrowExceptionOnDependencyVersionMismatch() {
		return throwExceptionOnDependencyVersionMismatch;
	}

	
	public void setThrowExceptionOnDependencyVersionMismatch(boolean throwExceptionOnDependencyVersionMismatch) {
		this.throwExceptionOnDependencyVersionMismatch = throwExceptionOnDependencyVersionMismatch;
	}
	

}
