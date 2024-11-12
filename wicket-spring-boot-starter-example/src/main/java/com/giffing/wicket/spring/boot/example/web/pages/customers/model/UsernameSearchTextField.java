package com.giffing.wicket.spring.boot.example.web.pages.customers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.html.autocomplete.AutoCompleteTextField;

public class UsernameSearchTextField extends AutoCompleteTextField {

	private static final Integer MINIMUM_INPUT_LENGTH = 3;
	
	@SpringBean
	private CustomerRepositoryService service;
	
	public UsernameSearchTextField(String id) {
		super(id, settings());
		initComponent();
	}

	public UsernameSearchTextField(String componentId, IModel<String> model) {
		super(componentId, model, settings());
		initComponent();
	}

	private void initComponent() {
		add(StringValidator.minimumLength(MINIMUM_INPUT_LENGTH));
	}

	@Override
	protected Iterator<String> getChoices(String username) {
		List<String> result = new ArrayList<>();
		if(username != null && username.length() >= MINIMUM_INPUT_LENGTH){
			result = service.findUsernames(10, username);
		}
		return result.iterator();
	}
	
	private static AutoCompleteSettings settings(){
		return new AutoCompleteSettings().setMinInputLength(MINIMUM_INPUT_LENGTH);
	}

}
