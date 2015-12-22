package com.giffing.wicket.spring.boot.example.web.pages.customers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.WicketBaseTest;
import com.giffing.wicket.spring.boot.example.web.html.modal.YesNoModal;
import com.giffing.wicket.spring.boot.example.web.pages.customers.create.CustomerCreatePage;

public class CustomerListPageTest extends WicketBaseTest {

	private static final long CUSTOMERS_COUNT = 5;

	private CustomerRepositoryService repository;
	
	@Override
	@Before
	public void setUp(){
		super.setUp();
		ApplicationContextMock applicationContextMock = getApplicationContextMock();
		
		repository = Mockito.mock(CustomerRepositoryService.class);
		
		Mockito.when(repository.findAll(Mockito.anyLong(), Mockito.anyLong(), Mockito.any())).thenReturn(createCustomers(CUSTOMERS_COUNT));
		Mockito.when(repository.count(Mockito.any())).thenReturn(CUSTOMERS_COUNT);
		for (long i=1; i <= CUSTOMERS_COUNT; i++) {
			Mockito.when(repository.findById(i)).thenReturn(createCustomer(i));
		}
		applicationContextMock.putBean(repository);
	}
	
	
	@Test
	@DirtiesContext
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void assert_start_customer_list_page(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		
		getTester().assertComponent("filterForm:table", DataTable.class);
		
		DataTable<Customer, CustomerSort> dataTable = (DataTable) getTester().getComponentFromLastRenderedPage("filterForm:table");
		assertThat(dataTable.getItemCount(), equalTo(CUSTOMERS_COUNT));
		//id, username, firstname, lastname, active, actions
		assertThat(dataTable.getColumns().size(), equalTo(6));
		//get third row
		Item<Customer> item3 = (Item) getTester().getComponentFromLastRenderedPage("filterForm:table:body:rows:3");
		assertThat(item3.getModelObject().getId(), equalTo(3L));
		assertThat(item3.getModelObject().getUsername(), equalTo("username3"));
		
		Item<Customer> item5 = (Item) getTester().getComponentFromLastRenderedPage("filterForm:table:body:rows:5");
		assertThat(item5.getModelObject().getId(), equalTo(5L));
		assertThat(item5.getModelObject().getUsername(), equalTo("username5"));
	}
	
	@Test
	@DirtiesContext
	public void assert_click_customer_edit_page(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		
		getTester().clickLink(getTableCell(5, 6) + "items:0:item:link");
		getTester().assertRenderedPage(CustomerCreatePage.class);
		
	}
	
	@Test
	@DirtiesContext
	public void assert_click_customer_create_page(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		
		getTester().clickLink("create");
		getTester().assertRenderedPage(CustomerCreatePage.class);
	}
	
	@Test
	@DirtiesContext
	public void assert_delete_customer_method_called_once(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		
		getTester().clickLink(getTableCell(5, 6) + "items:1:item:link");
		getTester().assertComponent("defaultModal", YesNoModal.class);
		getTester().clickLink("defaultModal:content:yes", true);
		
		verify(repository, times(1)).delete(Mockito.anyLong());
		verify(repository, times(1)).delete(5L);
		
	}
	
	private String getTableCell(int row, int cell){
		return "filterForm:table:body:rows:" + row + ":cells:" + cell + ":cell:";
	}
	
	public static List<Customer> createCustomers(long count) {
		List<Customer> customers = new ArrayList<>();
		for(long i = 1; i <= count; i++){
			customers.add(createCustomer(i));
		}
		return customers;
	}

	public static Customer createCustomer(long i) {
		Customer customer = new Customer();
		customer.setId(Long.valueOf(i));
		customer.setUsername("username" + i);
		customer.setFirstname("firstname" + i);
		customer.setLastname("lastname" + i);
		customer.setPassword("password" + i);
		return customer;
	}
	
}
