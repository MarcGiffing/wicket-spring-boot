package com.giffing.wicket.spring.boot.example.web;

import com.giffing.wicket.spring.boot.example.WicketApplication;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Test class for initialize Wicket & Spring Boot only in the web package. All
 * external spring beans have to be mocked.
 * 
 * @author Marc Giffing
 *
 */
@SpringBootTest(classes = WicketApplication.class)
public abstract class WicketBaseIntTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	private WicketTester tester;

	@Autowired
	private WebApplication wicketApplication;

	@Configuration
	public static class TestConfig {
		@Bean
		@Primary
		public WebSocketMessageBroadcaster webSocketMessageBroadcaster() {
			return Mockito.mock(WebSocketMessageBroadcaster.class);
		}
	}

	@BeforeEach
	public void setUp() {
		tester = new WicketTester(wicketApplication, new WicketMockServletContext(wicketApplication, null));
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
