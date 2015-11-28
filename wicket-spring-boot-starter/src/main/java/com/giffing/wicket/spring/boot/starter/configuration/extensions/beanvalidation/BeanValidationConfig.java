package com.giffing.wicket.spring.boot.starter.configuration.extensions.beanvalidation;

import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

/**
 * Enables the bean validation support if the following condition matches
 * 
 * 1. The {@link BeanValidationConfiguration} class is in the classpath. side
 * note: Bean validation required an validation implementation like hibernate
 * validator.
 * 
 * 2. The property wicket.beanvalidation.enabled has to be true (default = true)
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@Component
@ConditionalOnProperty(prefix = BeanValidationProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.apache.wicket.bean.validation.BeanValidationConfiguration.class)
@EnableConfigurationProperties({ BeanValidationProperties.class })
public class BeanValidationConfig implements WicketApplicationInitConfiguration {

	@Override
	public void init(WebApplication webApplication) {
		new BeanValidationConfiguration().configure(webApplication);
	}

}
