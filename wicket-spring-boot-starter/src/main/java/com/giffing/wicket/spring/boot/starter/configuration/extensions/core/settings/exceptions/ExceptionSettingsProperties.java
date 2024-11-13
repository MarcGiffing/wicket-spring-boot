package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.settings.ExceptionSettings.AjaxErrorStrategy;
import org.apache.wicket.settings.ExceptionSettings.ThreadDumpStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ExceptionSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class ExceptionSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.exceptions";
	
	private ThreadDumpStrategy threadDumpStrategy = ThreadDumpStrategy.THREAD_HOLDING_LOCK;
	
	private AjaxErrorStrategy errorHandlingStrategyDuringAjaxRequests = AjaxErrorStrategy.REDIRECT_TO_ERROR_PAGE;

}
