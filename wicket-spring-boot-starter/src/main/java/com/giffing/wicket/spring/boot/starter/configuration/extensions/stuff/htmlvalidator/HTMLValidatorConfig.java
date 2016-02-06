package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.htmlvalidator;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.htmlvalidator.HtmlValidationResponseFilter;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = HTMLValidatorProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.htmlvalidator.HtmlValidationResponseFilter.class)
@EnableConfigurationProperties({ HTMLValidatorProperties.class })
public class HTMLValidatorConfig implements WicketApplicationInitConfiguration {

	@Override
	public void init(WebApplication webApplication) {
		if (RuntimeConfigurationType.DEVELOPMENT == webApplication.getConfigurationType()) {
			webApplication.getMarkupSettings().setStripWicketTags(true);
			webApplication.getRequestCycleSettings().addResponseFilter(new HtmlValidationResponseFilter());
		}
	}

}
