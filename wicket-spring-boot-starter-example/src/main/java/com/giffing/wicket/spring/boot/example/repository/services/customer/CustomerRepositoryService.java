package com.giffing.wicket.spring.boot.example.repository.services.customer;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.FilterService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;

import java.util.List;

public interface CustomerRepositoryService extends FilterService<Customer, Long, CustomerFilter> {

    void delete(Long id);

    List<String> findUsernames(int count, String usernamePart);

    Customer save(Customer customer);

    Customer findByUsername(String username);

    /**
     * @param username the username
     * @return true if the username already exists
     */
    boolean usernameExists(String username);

}
