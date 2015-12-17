package com.giffing.wicket.spring.boot.example.web.pages.customers.model;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

public class UsernameSearchTextField<T> extends TextField<T> {

	public UsernameSearchTextField(String id) {
		super(id);
		initComponent();
	}

	public UsernameSearchTextField(String componentId, IModel<T> model) {
		super(componentId, model);
		initComponent();
	}

	private void initComponent() {
		add(StringValidator.minimumLength(3));
	}

}
