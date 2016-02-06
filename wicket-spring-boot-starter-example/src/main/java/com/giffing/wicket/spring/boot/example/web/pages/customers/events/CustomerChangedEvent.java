package com.giffing.wicket.spring.boot.example.web.pages.customers.events;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

import com.giffing.wicket.spring.boot.example.model.Customer;

public class CustomerChangedEvent implements IWebSocketPushMessage {

	private Customer customer;

	public CustomerChangedEvent(Customer customer) {
		this.setCustomer(customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
