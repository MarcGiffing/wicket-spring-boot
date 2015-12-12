package com.giffing.wicket.spring.boot.example.repository.services.customer;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.giffing.wicket.spring.boot.example.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	List<Customer> findAll(Specification<Customer> specs);
	
}
