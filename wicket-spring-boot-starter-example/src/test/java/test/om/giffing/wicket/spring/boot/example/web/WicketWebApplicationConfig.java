package test.om.giffing.wicket.spring.boot.example.web;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.giffing.wicket.spring.boot.example.web.SpringBootWebPackageIdentifier;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

//TODO move to test
@WicketSpringBootApplication
@EnableAutoConfiguration(exclude={
		DataSourceAutoConfiguration.class, 
		JpaRepositoriesAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class,
	})
@ComponentScan(basePackageClasses=SpringBootWebPackageIdentifier.class)
public class WicketWebApplicationConfig extends WicketBootSecuredWebApplication {

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return CustomerListPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SecureWebSession.class;
	}
	
}
