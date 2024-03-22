package com.giffing.wicket.spring.boot.example.web;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.giffing.wicket.spring.boot.example.web.pages.home.HomePage;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;

import test.com.giffing.wicket.spring.boot.example.web.WicketWebApplicationConfig;

/**
 * Test class for initialize Wicket & Spring Boot only in the web package. All
 * external spring beans have to be mocked.
 * 
 * @author Marc Giffing
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WicketWebApplicationConfig.class)
@Disabled
public class WicketBaseTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	private WicketTester tester;

	@Autowired
	private WebApplication wicketApplication;

	@Autowired
	private ApplicationContext applicationContextMock;
	
	@SpyBean
	private CustomAuthenticationManager customAuthenticationManager;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(wicketApplication, "applicationContext", applicationContextMock);
		tester = new WicketTester(wicketApplication, new WicketMockServletContext(wicketApplication, null));
		login(USERNAME, PASSWORD);
	}
	
	public static class CustomAuthenticationManager implements AuthenticationManager {

		@Override
		public Authentication authenticate(Authentication arg0) throws AuthenticationException {
			return new TestingAuthenticationToken(USERNAME, PASSWORD, "USER", "ADMIN");
		}
		
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
		tester.assertRenderedPage(HomePage.class);
	}

	public WicketTester getTester() {
		return tester;
	}

	public WebApplication getWicketApplication() {
		return wicketApplication;
	}

}
