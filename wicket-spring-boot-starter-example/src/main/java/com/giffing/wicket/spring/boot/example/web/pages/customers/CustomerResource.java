package com.giffing.wicket.spring.boot.example.web.pages.customers;

import java.util.ArrayList;
import java.util.List;

import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.annotations.ResourcePath;
import org.wicketstuff.rest.contenthandling.json.objserialdeserial.JacksonObjectSerialDeserial;
import org.wicketstuff.rest.contenthandling.json.webserialdeserial.JsonWebSerialDeserial;
import org.wicketstuff.rest.resource.AbstractRestResource;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;

/**
 * TODO how to inject spring beans - {@link CustomerRepositoryService}...
 * 
 * @author Marc Giffing
 *
 */
@ResourcePath("/rest")
public class CustomerResource extends AbstractRestResource<JsonWebSerialDeserial>  {


	public CustomerResource() {
		super(new JsonWebSerialDeserial(new JacksonObjectSerialDeserial()));
	}

	
	@MethodMapping("/customers")
    public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Customer c = new Customer();
		c.setId(1L);
		c.setFirstname("firstname");
		c.setLastname("lastname");
		c.setUsername("username");
		customers.add(c);
		return customers;
    }
	
}
