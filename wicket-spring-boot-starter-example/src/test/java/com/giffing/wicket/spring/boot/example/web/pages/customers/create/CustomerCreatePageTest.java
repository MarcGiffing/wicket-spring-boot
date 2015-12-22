package com.giffing.wicket.spring.boot.example.web.pages.customers.create;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.WicketBaseTest;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerCreatePageTest extends WicketBaseTest {

	private CustomerRepositoryService repository;
	
	@Override
	@Before
	public void setUp(){
		super.setUp();
		ApplicationContextMock applicationContextMock = getApplicationContextMock();
		System.out.println(repository + " " + applicationContextMock);
		repository = Mockito.mock(CustomerRepositoryService.class);
		
		applicationContextMock.putBean(repository);
	}
	
	@Test
	@DirtiesContext
	public void assert_customer_save_called(){
		getTester().startPage(CustomerCreatePage.class);
		getTester().assertRenderedPage(CustomerCreatePage.class);

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
		
		//it should be checked, that the username does not already exists
		verify(repository, times(1)).usernameExists("myUsername");
		
		verify(repository, times(1)).save(Mockito.any());
		verify(repository, times(1)).save(customerArgument.capture());
		
		Customer value = customerArgument.getValue();
		assertThat(value.getId(), nullValue());
		assertThat(value.getUsername(), equalTo("myUsername"));
		assertThat(value.getFirstname(), equalTo("myFirstname"));
		assertThat(value.getLastname(), equalTo("myLastname"));
		assertThat(value.getPassword(), equalTo("myPassword"));
	}
	
	@Test
	@DirtiesContext
	public void assert_customer_not_saved_if_user_already_exist() {
		getTester().startPage(CustomerCreatePage.class);
		getTester().assertRenderedPage(CustomerCreatePage.class);

		String newUsername = "newUsername";

		when(repository.usernameExists(newUsername)).thenReturn(true);
		
		FormTester formTester = getTester().newFormTester("form");
		formTester.setValue(borderPath("username"), newUsername);
		formTester.setValue(borderPath("firstname"), "myFirstname");
		formTester.setValue(borderPath("lastname"), "myLastname");
		formTester.setValue(borderPath("password"), "myPassword");
		formTester.submit("submit");
		
		getTester().assertErrorMessages("Username already exists!");
		getTester().assertRenderedPage(CustomerCreatePage.class);
		
		//it should be checked, that the username does not already exists
		verify(repository, times(1)).usernameExists(newUsername);
		verify(repository, times(0)).save(Mockito.any());
	}
	
	private String borderPath(String componentName){
		return componentName + "Border:" + componentName + "Border_body:" + componentName;
	}

}
