package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.beanvalidation;

import javax.validation.Validator;

import org.apache.wicket.bean.validation.BeanValidationConfiguration;

public class WicketSpringBeanValidationConfiguration extends BeanValidationConfiguration {
	
	private Validator validator;

	public WicketSpringBeanValidationConfiguration(Validator validator) {
		this.validator = validator;
	}
	
	@Override
	public Validator getValidator() {
		return validator;
	}

}
