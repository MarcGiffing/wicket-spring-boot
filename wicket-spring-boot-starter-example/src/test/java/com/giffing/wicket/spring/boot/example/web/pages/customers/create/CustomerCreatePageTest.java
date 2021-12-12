package com.giffing.wicket.spring.boot.example.web.pages.customers.create;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.apache.wicket.util.tester.FormTester;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.WicketBaseTest;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;

public class CustomerCreatePageTest extends WicketBaseTest {

	@MockBean
	private CustomerRepositoryService repository;
	
	@Override
	@BeforeEach
	public void setUp(){
		super.setUp();
	}
	
	@Test
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
		
		//it should be checked, that the username does already exists
		verify(repository, times(1)).usernameExists(newUsername);
		verify(repository, times(0)).save(Mockito.any());
	
		verifyNoMoreInteractions(repository);
	}
	
	private String borderPath(String componentName){
		return componentName + "Border:" + componentName + "Border_body:" + componentName;
	}

}
