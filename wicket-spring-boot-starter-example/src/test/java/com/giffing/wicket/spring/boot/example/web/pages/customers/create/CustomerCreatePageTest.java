package com.giffing.wicket.spring.boot.example.web.pages.customers.create;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.WicketBaseTest;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;

public class CustomerCreatePageTest extends WicketBaseTest {

private CustomerRepositoryService repository;
	
	@Override
	@Before
	public void setUp(){
		super.setUp();
		ApplicationContextMock applicationContextMock = getApplicationContextMock();
		
		repository = Mockito.mock(CustomerRepositoryService.class);
		
		applicationContextMock.putBean(repository);
	}
	
	@Test
	public void assert_customer_save_called(){
		getTester().startPage(CustomerCreatePage.class);
		getTester().assertRenderedPage(CustomerCreatePage.class);
		getTester().debugComponentTrees();
		FormTester formTester = getTester().newFormTester("form");
		formTester.setValue(borderPath("username"), "myUsername");
		formTester.setValue(borderPath("firstname"), "myFirstname");
		formTester.setValue(borderPath("lastname"), "myLastname");
		formTester.setValue(borderPath("password"), "myPassword");
		formTester.submit("submit");
		getTester().assertNoErrorMessage();
		getTester().assertNoInfoMessage();
		getTester().assertRenderedPage(CustomerListPage.class);
		
		ArgumentCaptor<Customer> customerArgument = ArgumentCaptor.forClass(Customer.class);
		
		//it should be checked, that the username not alredy exists
		verify(repository, times(1)).usernameExists("myUsername");
		
		verify(repository, times(1)).save(Mockito.any());
		verify(repository, times(1)).save(customerArgument.capture());
		
		Customer value = customerArgument.getValue();
		assertThat(value.getUsername(), equalTo("myUsername"));
		assertThat(value.getFirstname(), equalTo("myFirstname"));
		assertThat(value.getLastname(), equalTo("myLastname"));
		assertThat(value.getPassword(), equalTo("myPassword"));
		
		
	}
	private String borderPath(String componentName){
		return componentName + "Border:" + componentName + "Border_body:" + componentName;
	}
	
}
