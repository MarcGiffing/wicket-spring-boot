package com.giffing.wicket.spring.boot.example.web.pages.customers.create;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.web.WicketBaseIntTest;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import org.apache.wicket.util.tester.FormTester;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Transactional
@Rollback
public class CustomerCreatePageIntTest extends WicketBaseIntTest {

    @Autowired
    private CustomerRepositoryService service;

    @Test
    public void assert_new_customer_saved() {

        long initialCustomerCount = service.count(new CustomerFilter());

        getTester().startPage(CustomerCreatePage.class);
        getTester().assertRenderedPage(CustomerCreatePage.class);
        FormTester formTester = getTester().newFormTester("form");

        String usernameOfNewCreatedUser = "newUser";
        formTester.setValue(borderPath("username"), usernameOfNewCreatedUser);
        formTester.setValue(borderPath("firstname"), "newFirstname");
        formTester.setValue(borderPath("lastname"), "newLastname");
        formTester.submit("submit");
        getTester().assertNoErrorMessage();
        getTester().assertNoInfoMessage();
        getTester().assertRenderedPage(CustomerListPage.class);

        long currentCustomerCount = service.count(new CustomerFilter());
        assertThat(currentCustomerCount, equalTo(initialCustomerCount + 1));

        Customer newUser = service.findByUsername(usernameOfNewCreatedUser);
        assertThat(newUser, notNullValue());
        assertThat(newUser.getId(), notNullValue());
        assertThat(newUser.getUsername(), equalTo(usernameOfNewCreatedUser));
    }

    @Test
    public void assert_error_when_create_existing_customer() {
        getTester().startPage(CustomerCreatePage.class);
        getTester().assertRenderedPage(CustomerCreatePage.class);
        getTester().debugComponentTrees();
        FormTester formTester = getTester().newFormTester("form");

        //user is created via liquibase as initial data
        String usernameOfExistingUser = "frodo";
        formTester.setValue(borderPath("username"), usernameOfExistingUser);
        formTester.setValue(borderPath("firstname"), "newFirstname");
        formTester.setValue(borderPath("lastname"), "newLastname");
        formTester.submit("submit");

        getTester().assertErrorMessages("Username already exists!");

    }

    protected String borderPath(String componentName) {
        return componentName + "Border:" + componentName + "Border_body:" + componentName;
    }

}
