package com.giffing.wicket.spring.boot.example.web;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.giffing.wicket.spring.boot.starter.pages.HomePage;
import com.giffing.wicket.spring.boot.starter.pages.LoginPage;
import com.giffing.wicket.spring.boot.starter.security.SecureWebSession;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WicketWebApplicationConfig.class)
@WebAppConfiguration
public class WicketApplicationTest {

	private WicketTester tester;
	
	@Autowired
	private WicketWebApplicationConfig wicketApplication;
	
	@Before
	public void setUp(){
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
	
	@Test
	public void assert_login_logout_test(){
		//start and render the test page
		tester.startPage(HomePage.class);
		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
		
		tester.assertComponent("logout", AjaxLink.class);
		tester.clickLink("logout", true);
		
		//we can't access the login page cause it required a authenticated user
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(LoginPage.class);
	}
	public WicketTester getTester() {
		return tester;
	}

}
