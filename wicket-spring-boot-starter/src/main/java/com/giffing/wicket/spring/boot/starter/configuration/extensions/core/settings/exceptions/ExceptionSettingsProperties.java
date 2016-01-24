package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.exceptions;

import org.apache.wicket.settings.ExceptionSettings.AjaxErrorStrategy;
import org.apache.wicket.settings.ExceptionSettings.ThreadDumpStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ExceptionSettingsProperties.PROPERTY_PREFIX)

public class ExceptionSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.exceptions";
	
	private ThreadDumpStrategy threadDumpStrategy = ThreadDumpStrategy.THREAD_HOLDING_LOCK;
	
	private AjaxErrorStrategy errorHandlingStrategyDuringAjaxRequests = AjaxErrorStrategy.REDIRECT_TO_ERROR_PAGE;

	public AjaxErrorStrategy getErrorHandlingStrategyDuringAjaxRequests() {
		return errorHandlingStrategyDuringAjaxRequests;
	}

	public void setErrorHandlingStrategyDuringAjaxRequests(AjaxErrorStrategy errorHandlingStrategyDuringAjaxRequests) {
		this.errorHandlingStrategyDuringAjaxRequests = errorHandlingStrategyDuringAjaxRequests;
	}

	public ThreadDumpStrategy getThreadDumpStrategy() {
		return threadDumpStrategy;
	}

	public void setThreadDumpStrategy(ThreadDumpStrategy threadDumpStrategy) {
		this.threadDumpStrategy = threadDumpStrategy;
	}
	
}
