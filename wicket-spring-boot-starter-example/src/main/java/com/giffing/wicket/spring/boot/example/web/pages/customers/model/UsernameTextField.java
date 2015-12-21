package com.giffing.wicket.spring.boot.example.web.pages.customers.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.StringValidator;

import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;

public class UsernameTextField extends TextField<String>{

	@SpringBean
	private CustomerRepositoryService service;
	
	public UsernameTextField(String id) {
		super(id);
		Injector.get().inject(this);
		add(StringValidator.minimumLength(3));
		setRequired(true);
		add(new UsernameExistsValidator());
	}

	public class UsernameExistsValidator implements IValidator<String>{

		@Override
		public void validate(IValidatable<String> validatable) {
			final String field = validatable.getValue();
			if(service.usernameExists(field)){
				ValidationError error = new ValidationError();
				error.setMessage(getString(getClass().getSimpleName()));
				validatable.error(error);
			}
			
		}
		
	}
	
}
