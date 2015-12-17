package com.giffing.wicket.spring.boot.example.web.pages.customers;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredPropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.html.border.LabledFormBroder;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.filter.AbstractCheckBoxFilter;
import com.giffing.wicket.spring.boot.example.web.pages.customers.filter.AbstractTextFieldFilter;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.CustomerDataProvider;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.UsernameSearchTextField;

@MountPath("customers")
public class CustomerListPage extends BasePage {

	private IModel<CustomerFilter> customerFilterModel;
	
	private FilterForm<CustomerFilter> filterForm; 
	
	public CustomerListPage() {
		customerFilterModel = new CompoundPropertyModel<>(new CustomerFilter());
		CustomerDataProvider customerDataProvider = new CustomerDataProvider(customerFilterModel);
		
		queue( new ValidationForm<CustomerFilter>("form", customerFilterModel));
		queue(new LabledFormBroder<>(getString("id"), new TextField<Long>("id")));
		queue(new LabledFormBroder<>(getString("username"), new UsernameSearchTextField<String>("usernameLike")));
		queue(new LabledFormBroder<>(getString("firstname"), new TextField<String>("firstnameLike").add(StringValidator.minimumLength(3))));
		queue(new LabledFormBroder<>(getString("lastname"), new TextField<String>("lastnameLike").add(StringValidator.minimumLength(3))));
		queue(new LabledFormBroder<>(getString("active"), new CheckBox("active")));
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
				filterForm.clearInput();
			}
			
		};
		cancelButton.setDefaultFormProcessing(false);
		return cancelButton;
	}
	
	private void customerDataTable(CustomerDataProvider customerDataProvider) {
		
		filterForm = new FilterForm<CustomerFilter>("filterForm", customerDataProvider);
		queue(filterForm);
		
		List<IColumn<Customer, CustomerSort>> columns = new ArrayList<>();
		columns.add(new PropertyColumn<Customer, CustomerSort>(Model.of("Id"), CustomerSort.ID, CustomerSort.ID.getFieldName()));
		columns.add(new FilteredPropertyColumn<Customer, CustomerSort>(new ResourceModel("username"), CustomerSort.USERNAME , CustomerSort.USERNAME.getFieldName()){


			@Override
			public Component getFilter(String componentId, FilterForm<?> form) {
				return new AbstractTextFieldFilter<String>(componentId, new PropertyModel<>(form.getModel(), "usernameLike"), form){

					@Override
					public TextField<String> createTextFieldComponent(String componentId, IModel<String> model) {
						return new UsernameSearchTextField<String>(componentId, model);
					}
					
				};
			}
			
		});
		columns.add(new FilteredPropertyColumn<Customer, CustomerSort>(new ResourceModel("firstname"), CustomerSort.FIRSTNAME , CustomerSort.FIRSTNAME.getFieldName()){
			
			@Override
			public Component getFilter(String componentId, FilterForm<?> form) {
				return new AbstractTextFieldFilter<String>(componentId, new PropertyModel<>(form.getModel(), "firstnameLike"), form){

					@Override
					public TextField<String> createTextFieldComponent(String componentId, IModel<String> model) {
						return new TextField<String>(componentId, model);
					}
					
				};
			}
			
		});
		columns.add(new FilteredPropertyColumn<Customer, CustomerSort>(new ResourceModel("lastname"), CustomerSort.LASTNAME , CustomerSort.LASTNAME.getFieldName()){

			@Override
			public Component getFilter(String componentId, FilterForm<?> form) {
				return new AbstractTextFieldFilter<String>(componentId, new PropertyModel<>(form.getModel(), "lastnameLike"), form){

					@Override
					public TextField<String> createTextFieldComponent(String componentId, IModel<String> model) {
						return new TextField<String>(componentId, model);
					}
					
				};
			}

		});
		
		
		columns.add(new FilteredPropertyColumn<Customer, CustomerSort>(new ResourceModel("active"), CustomerSort.ACTIVE , CustomerSort.ACTIVE.getFieldName()){


			@Override
			public Component getFilter(String componentId, FilterForm<?> form) {
				return new AbstractCheckBoxFilter(componentId, new PropertyModel<>(form.getModel(), "active"), form);
			}
			
			@Override
			public IModel<?> getDataModel(IModel<Customer> rowModel) {
				// TODO Auto-generated method stub
				IModel<?> dataModel = super.getDataModel(rowModel);
				boolean active = (boolean)dataModel.getObject();
				IModel result = null;
				if(active){
					result = new ResourceModel("yes");
				} else {
					result = new ResourceModel("no");
				}
				return result;
			}
			
		});
		
		DataTable<Customer, CustomerSort> dataTable = new DefaultDataTable<Customer, CustomerSort>("table", columns , customerDataProvider, 10){
		
		};
		FilterToolbar filterToolbar = new FilterToolbar(dataTable, filterForm);
		
		dataTable.addTopToolbar(filterToolbar);
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
