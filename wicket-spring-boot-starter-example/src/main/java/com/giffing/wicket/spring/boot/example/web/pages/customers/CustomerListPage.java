package com.giffing.wicket.spring.boot.example.web.pages.customers;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.html.border.LabledFormBroder;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.CustomerDataProvider;

@MountPath("customers")
public class CustomerListPage extends BasePage {

	private IModel<CustomerFilter> customerFilterModel;
	
	public CustomerListPage() {
		customerFilterModel = new CompoundPropertyModel<>(new CustomerFilter());
		CustomerDataProvider customerDataProvider = new CustomerDataProvider(customerFilterModel);
		
		queue(new ValidationForm<CustomerFilter>("form", customerFilterModel));
		queue(new LabledFormBroder<>(getString("id"), new TextField<Long>("id")));
		queue(new LabledFormBroder<>(getString("username"), new TextField<String>("usernameLike").add(StringValidator.minimumLength(4))));
		queue(cancelButton());
		customerDataView(customerDataProvider);
		customerDataTable(customerDataProvider);
		
	}

	private Button cancelButton() {
		Button cancelButton = new Button("cancel"){

			@Override
			public void onSubmit() {
				customerFilterModel.setObject(new CustomerFilter());
				getForm().clearInput();
			}
			
		};
		cancelButton.setDefaultFormProcessing(false);
		return cancelButton;
	}
	
	private void customerDataTable(CustomerDataProvider customerDataProvider) {
		List<IColumn<Customer, CustomerSort>> columns = new ArrayList<>();
		columns.add(new PropertyColumn<Customer, CustomerSort>(Model.of("Id"), CustomerSort.ID, "id"));
		columns.add(new PropertyColumn<Customer, CustomerSort>(Model.of("Username"), CustomerSort.USERNAME, "username"));
		DataTable<Customer, CustomerSort> dataTable = new DefaultDataTable<Customer, CustomerSort>("table", columns , customerDataProvider, 10){
		
		};
		queue(dataTable);
	}

	private void customerDataView(CustomerDataProvider customerDataProvider) {
		DataView<Customer> dataView = new DataView<Customer>("rows", customerDataProvider) {

		  @Override
		  protected void populateItem(Item<Customer> item) {
			  item.add(new Label("id"));
			  item.add(new Label("username"));
		  }
		};
		queue(dataView);
	}

	
	
}
