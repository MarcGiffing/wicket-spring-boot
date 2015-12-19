package com.giffing.wicket.spring.boot.example.repository.services.customer;

import java.util.List;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.FilterService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;

public interface CustomerRepositoryService extends FilterService<Customer, CustomerFilter>{

	void delete(Long id);

	List<String> findUsernames(int count, String usernamePart);

}
