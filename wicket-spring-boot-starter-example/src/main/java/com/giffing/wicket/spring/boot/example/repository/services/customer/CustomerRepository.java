package com.giffing.wicket.spring.boot.example.repository.services.customer;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.jpa.support.CustomJpaSpecificationExecutor;

public interface CustomerRepository extends CrudRepository<Customer, Long>, CustomJpaSpecificationExecutor<Customer> {
	
	@Override
	List<Customer> findAll(Specification<Customer> specs);

	int countByUsernameIgnoreCase(String username);

	Customer findByUsernameIgnoreCase(String username);
	
}
