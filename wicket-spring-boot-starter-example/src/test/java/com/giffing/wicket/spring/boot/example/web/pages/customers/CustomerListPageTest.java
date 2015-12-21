package com.giffing.wicket.spring.boot.example.web.pages.customers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.WicketBaseTest;

public class CustomerListPageTest extends WicketBaseTest{

	private static final long CUSTOMERS_COUNT = 5;

	@Before
	public void setUp(){
		super.setUp();
		ApplicationContextMock applicationContextMock = getApplicationContextMock();
		
		CustomerRepositoryService repository = Mockito.mock(CustomerRepositoryService.class);
		
		Mockito.when(repository.findAll(Mockito.anyLong(), Mockito.anyLong(), Mockito.any())).thenReturn(createCustomers(CUSTOMERS_COUNT));
		Mockito.when(repository.count(Mockito.any())).thenReturn(CUSTOMERS_COUNT);
		for (long i=1; i <= CUSTOMERS_COUNT; i++) {
			Mockito.when(repository.findById(i)).thenReturn(createCustomer(i));
		}
		applicationContextMock.putBean(repository);
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void assert_start_customer_list_page(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		
		getTester().assertComponent("filterForm:table", DataTable.class);
		
		DataTable dataTable = (DataTable) getTester().getComponentFromLastRenderedPage("filterForm:table");
		assertThat(dataTable.getItemCount(), equalTo(CUSTOMERS_COUNT));
		
	}
	
	private List<Customer> createCustomers(long count) {
		List<Customer> customers = new ArrayList<>();
		for(long i = 1; i <= count; i++){
			customers.add(createCustomer(i));
		}
		return customers;
	}

	private Customer createCustomer(long i) {
		Customer customer = new Customer();
		customer.setId(Long.valueOf(i));
		customer.setUsername("customer" + i);
		customer.setFirstname("firstname" + i);
		customer.setLastname("lastname" + i);
		customer.setPassword("password" + i);
		return customer;
	}
	
}
