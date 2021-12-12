package test.com.giffing.wicket.spring.boot.example.web;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.giffing.wicket.spring.boot.example.WicketApplication;
import com.giffing.wicket.spring.boot.example.web.SpringBootWebPackageIdentifier;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.login.LoginPage;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;

//TODO move to test
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
})
@ComponentScan(basePackageClasses=SpringBootWebPackageIdentifier.class)
@EnableTransactionManagement
public class WicketWebApplicationConfig extends WicketBootSecuredWebApplication {

	@Override
	protected void init() {
		super.init();

		// Add WicketApplication.properties resource file
		getResourceSettings().getStringResourceLoaders().add(new ClassStringResourceLoader(WicketApplication.class));
	}

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
