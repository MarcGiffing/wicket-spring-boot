package com.giffing.wicket.spring.boot.example.web;

import java.util.Map;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.giffing.wicket.spring.boot.example.web.pages.home.HomePage;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;

import test.com.giffing.wicket.spring.boot.example.web.WicketWebApplicationConfig;

/**
 * Test class for initialize Wicket & Spring Boot only in the web package. All
 * external spring beans have to be mocked.
 * 
 * @author Marc Giffing
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WicketWebApplicationConfig.class)
@Ignore
public class WicketBaseTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	private WicketTester tester;
	
	@Autowired
	private WebApplication wicketApplication;

	private static ApplicationContextMock applicationContextMock;

	@Before
	public void setUp() {
		if (applicationContextMock == null) {
			applicationContextMock = new ApplicationContextMock();
			applicationContextMock.putBean(new WebSocketMessageBroadcaster() {

				@Override
				public void send(IWebSocketPushMessage event) {
					// do nothing
				}
			});

			applicationContextMock.putBean("authenticationManager", new AuthenticationManager() {

				@Override
				public Authentication authenticate(Authentication arg0) throws AuthenticationException {
					return new TestingAuthenticationToken(USERNAME, PASSWORD, "USER", "ADMIN");
				}
			});
			ReflectionTestUtils.setField(wicketApplication, "applicationContext", applicationContextMock);
			System.out.println(ReflectionTestUtils.getField(wicketApplication, "applicationContext"));
		}
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
		tester.assertRenderedPage(HomePage.class);
	}

	public WicketTester getTester() {
		return tester;
	}

	public WebApplication getWicketApplication() {
		return wicketApplication;
	}

	public ApplicationContextMock getApplicationContextMock() {
		return applicationContextMock;
	}
	
	/**
	 * Creates a new mock of the given class. Resets and returns an existing one.
	 * @param mock
	 * @return
	 */
	public <T> T  registerMock(Class<T> mock) {
		Map allBeansOfType = applicationContextMock.getBeansOfType(mock);
		
		//TODO throw an exception if multiple beans found
		if(allBeansOfType.size() > 0) {
			T next = (T)allBeansOfType.values().iterator().next();
			Mockito.reset(next);
			return next;
		}
		
		//create a new mock and put it to the mocked application context
		T mock2 = Mockito.mock(mock);
		applicationContextMock.putBean(mock2);
		return mock2;
	}

}
