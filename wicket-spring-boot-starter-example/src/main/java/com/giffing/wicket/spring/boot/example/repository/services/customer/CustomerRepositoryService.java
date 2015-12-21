package com.giffing.wicket.spring.boot.example.repository.services.customer;

import java.util.List;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.FilterService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;

public interface CustomerRepositoryService extends FilterService<Customer, Long, CustomerFilter>{

	void delete(Long id);

	List<String> findUsernames(int count, String usernamePart);

	Customer save(Customer customer);

	/**
	 * @param username
	 * @return true if the username already exists
	 */
	boolean usernameExists(String username);

}
