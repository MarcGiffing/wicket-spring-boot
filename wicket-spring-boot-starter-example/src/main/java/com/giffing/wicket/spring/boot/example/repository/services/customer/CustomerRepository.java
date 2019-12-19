package com.giffing.wicket.spring.boot.example.repository.services.customer;

import com.giffing.wicket.spring.boot.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	int countByUsernameIgnoreCase(String username);

	Customer findByUsernameIgnoreCase(String username);
	
}
