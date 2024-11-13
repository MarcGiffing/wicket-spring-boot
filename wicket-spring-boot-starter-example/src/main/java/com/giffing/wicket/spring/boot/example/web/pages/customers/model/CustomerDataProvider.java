package com.giffing.wicket.spring.boot.example.web.pages.customers.model;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.wicket.dataprovider.DefaultDataProvider;
import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

@Getter
@Setter
public class CustomerDataProvider extends DefaultDataProvider<Customer, Long, CustomerFilter, CustomerSort, CustomerRepositoryService> {

    @SpringBean
    private CustomerRepositoryService filterService;

    private IModel<CustomerFilter> customerFilterModel;

    public CustomerDataProvider(IModel<CustomerFilter> customerFilterModel) {
        this.customerFilterModel = customerFilterModel;
        Injector.get().inject(this);
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
