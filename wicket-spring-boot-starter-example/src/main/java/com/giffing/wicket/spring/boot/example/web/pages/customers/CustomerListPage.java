package com.giffing.wicket.spring.boot.example.web.pages.customers;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.CustomerDataProvider;

@MountPath("customers")
public class CustomerListPage extends WebPage{

	public CustomerListPage() {
		
		DataView<Customer> dataView = new DataView<Customer>("rows", new CustomerDataProvider()) {

		  @Override
		  protected void populateItem(Item<Customer> item) {
			  item.add(new Label("id"));
			  item.add(new Label("username"));
		  }
		};
		add(dataView);
	}
	
}
