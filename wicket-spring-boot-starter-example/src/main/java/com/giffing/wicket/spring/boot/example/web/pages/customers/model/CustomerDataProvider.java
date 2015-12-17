package com.giffing.wicket.spring.boot.example.web.pages.customers.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.wicket.dataprovider.DefaultDataProvider;

public class CustomerDataProvider extends DefaultDataProvider<Customer, CustomerRepositoryService, CustomerFilter, CustomerSort>{
	
	@SpringBean
	private CustomerRepositoryService customerRepositoryService;
	
	private IModel<CustomerFilter> customerFilterModel;

	public CustomerDataProvider(){
		customerFilterModel = new CompoundPropertyModel<>(new CustomerFilter());
		Injector.get().inject(this);
	}
	
	public CustomerDataProvider(IModel<CustomerFilter> customerFilterModel){
		this.customerFilterModel = customerFilterModel;
		Injector.get().inject(this);
	}
	
	@Override
	public CustomerRepositoryService getFilterService() {
		return customerRepositoryService;
	}

	@Override
	public CustomerFilter getFilter() {
		return customerFilterModel.getObject();
	}

	@Override
	public void setFilter(CustomerFilter filterModel) {
		customerFilterModel.setObject(filterModel);
	}

}
