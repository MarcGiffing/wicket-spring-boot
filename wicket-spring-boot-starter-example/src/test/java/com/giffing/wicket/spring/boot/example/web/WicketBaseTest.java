package com.giffing.wicket.spring.boot.example.web;

import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.starter.pages.HomePage;
import com.giffing.wicket.spring.boot.starter.pages.LoginPage;
import com.giffing.wicket.spring.boot.starter.security.SecureWebSession;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WicketWebApplicationConfig.class)
@WebAppConfiguration
public class WicketBaseTest {

	private WicketTester tester;
	
	@Autowired
	private WicketWebApplicationConfig wicketApplication;
	
	 private ApplicationContextMock applicationContextMock;
	
	@Before
	public void setUp(){
		applicationContextMock = new ApplicationContextMock();
		applicationContextMock.putBean("authenticationManager", new AuthenticationManager() {
			
			@Override
			public Authentication authenticate(Authentication arg0) throws AuthenticationException {
				return new TestingAuthenticationToken("admin", "admin", "USER", "ADMIN");
			}
		});
		ReflectionTestUtils.setField(wicketApplication, "applicationContext", applicationContextMock);
		tester = new WicketTester(wicketApplication);

		login("admin", "admin");
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
	public WicketWebApplicationConfig getWicketApplication() {
		return wicketApplication;
	}
	public ApplicationContextMock getApplicationContextMock() {
		return applicationContextMock;
	}
	
}
