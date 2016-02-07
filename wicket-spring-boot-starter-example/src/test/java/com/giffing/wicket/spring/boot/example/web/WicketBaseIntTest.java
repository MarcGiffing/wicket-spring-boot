package com.giffing.wicket.spring.boot.example.web;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.giffing.wicket.spring.boot.example.WicketApplication;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;

/**
 * Test class for initialize Wicket & Spring Boot only in the web package. All
 * external spring beans have to be mocked.
 * 
 * @author Marc Giffing
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WicketApplication.class)
@EnableWebSecurity
@Ignore
@DirtiesContext
public class WicketBaseIntTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	private WicketTester tester;

	@Autowired
	private WebApplication wicketApplication;

	@Before
	public void setUp() {
		tester = new WicketTester(wicketApplication);
		login(USERNAME, PASSWORD);
	}

	private void login(String username, String password) {
		SecureWebSession session = (SecureWebSession) tester.getSession();
		session.signOut();
		tester.startPage(LoginPage.class);
		FormTester formTester = tester.newFormTester("loginForm");
		formTester.setValue("username", username);
		formTester.setValue("password", password);
		formTester.submit();
		tester.assertNoErrorMessage();
		tester.assertRenderedPage(tester.getApplication().getHomePage());
	}

	public WicketTester getTester() {
		return tester;
	}

	public WebApplication getWicketApplication() {
		return wicketApplication;
	}

}
