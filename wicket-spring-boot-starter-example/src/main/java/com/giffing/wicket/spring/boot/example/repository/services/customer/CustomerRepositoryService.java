package com.giffing.wicket.spring.boot.example.repository.services.customer;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.FilterService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;

public interface CustomerRepositoryService extends FilterService<Customer, CustomerFilter>{

}
