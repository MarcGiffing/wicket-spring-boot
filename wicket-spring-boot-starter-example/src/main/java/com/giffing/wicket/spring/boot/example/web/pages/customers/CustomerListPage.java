package com.giffing.wicket.spring.boot.example.web.pages.customers;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.web.html.border.LabledFormBroder;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.CustomerDataProvider;

@MountPath("customers")
public class CustomerListPage extends BasePage {

	private IModel<CustomerFilter> customerFilterModel;
	
	public CustomerListPage() {
		customerFilterModel = new CompoundPropertyModel<>(new CustomerFilter());
		
		queue(new ValidationForm<CustomerFilter>("form", customerFilterModel));
		queue(new LabledFormBroder<>(getString("id"), new TextField<Long>("id")));
		queue(new LabledFormBroder<>(getString("username"), new TextField<String>("username").add(StringValidator.maximumLength(12))));
		
		DataView<Customer> dataView = new DataView<Customer>("rows", new CustomerDataProvider(customerFilterModel)) {

		  @Override
		  protected void populateItem(Item<Customer> item) {
			  item.add(new Label("id"));
			  item.add(new Label("username"));
		  }
		};
		queue(dataView);
	}
	
}
