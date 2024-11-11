package com.giffing.wicket.spring.boot.example.web.pages.customers.events;

import com.giffing.wicket.spring.boot.example.model.Customer;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

public class CustomerDeletedEvent implements IWebSocketPushMessage {

	private Customer customer;

	public CustomerDeletedEvent(Customer customer) {
		this.setCustomer(customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
