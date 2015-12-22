package com.giffing.wicket.spring.boot.example.web.pages.customers.edit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.WicketBaseTest;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPageTest;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.UsernameTextField;

public class CustomerEditPageTest extends WicketBaseTest {

	private static final Long CUSTOMERS_COUNT = 5L;
	
	private CustomerRepositoryService repository;
	
	@Override
	@Before
	public void setUp(){
		super.setUp();
		ApplicationContextMock applicationContextMock = getApplicationContextMock();
		repository = Mockito.mock(CustomerRepositoryService.class);
		
		Mockito.when(repository.findAll(Mockito.anyLong(), Mockito.anyLong(), Mockito.any())).thenReturn(CustomerListPageTest.createCustomers(CUSTOMERS_COUNT));
		Mockito.when(repository.count(Mockito.any())).thenReturn(CUSTOMERS_COUNT);
		for (long i=1; i <= CUSTOMERS_COUNT; i++) {
			Mockito.when(repository.findById(i)).thenReturn(CustomerListPageTest.createCustomer(i));
		}
		
		applicationContextMock.putBean(repository);
	}
	
	
	@Test
	@DirtiesContext
	public void assert_customer_not_exists(){
		PageParameters params = new PageParameters();
		params.add(CustomerEditPage.CUSTOMER_ID_PARAM, "9548");
		getTester().startPage(CustomerEditPage.class, params);
		getTester().assertRenderedPage(CustomerListPage.class);
		getTester().assertErrorMessages("Customer not found 9548");
	}
	
	@Test
	@DirtiesContext
	public void assert_customer_on_load_existing(){
		PageParameters params = new PageParameters();
		params.add(CustomerEditPage.CUSTOMER_ID_PARAM, "3");
		getTester().startPage(CustomerEditPage.class, params);

		getTester().assertNoErrorMessage();
		getTester().assertNoInfoMessage();
		
		getTester().assertRenderedPage(CustomerEditPage.class);
		
		FormTester formTester = getTester().newFormTester("form");
		
		String username = formTester.getTextComponentValue(borderPath("username"));
		String firstname = formTester.getTextComponentValue(borderPath("firstname"));
		String lastname = formTester.getTextComponentValue(borderPath("lastname"));
		
		assertThat(username, equalTo("username3"));
		assertThat(firstname, equalTo("firstname3"));
		assertThat(lastname, equalTo("lastname3"));
		
		getTester().debugComponentTrees();
		
		String usernameFieldPath = "form:" + borderPath("username");
		getTester().assertComponent(usernameFieldPath, UsernameTextField.class);
		getTester().isDisabled(usernameFieldPath);
		
		String passwordFieldPath = "form:" + borderPath("password");
		getTester().isInvisible(passwordFieldPath);
	}
	
	@Test
	@DirtiesContext
	public void assert_customer_saved(){
		PageParameters params = new PageParameters();
		params.add(CustomerEditPage.CUSTOMER_ID_PARAM, "3");
		getTester().startPage(CustomerEditPage.class, params);
		
		FormTester formTester = getTester().newFormTester("form");
		formTester.setValue(borderPath("firstname"), "the-new-firstname");
		formTester.submit("submit");
		
		ArgumentCaptor<Customer> customerArgument = ArgumentCaptor.forClass(Customer.class);
		
		//it should be checked, that the username does not already exists
		verify(repository, times(0)).usernameExists(Mockito.any());
		
		verify(repository, times(1)).save(Mockito.any());
		verify(repository, times(1)).save(customerArgument.capture());
		
		Customer value = customerArgument.getValue();
		assertThat(value.getId(), equalTo(3L));
		assertThat(value.getUsername(), equalTo("username3"));
		assertThat(value.getFirstname(), equalTo("the-new-firstname"));
		assertThat(value.getLastname(), equalTo("lastname3"));
		assertThat(value.getPassword(), equalTo("password3"));
		
		
	}
	
	private String borderPath(String componentName){
		return componentName + "Border:" + componentName + "Border_body:" + componentName;
	}

}
