package test.om.giffing.wicket.spring.boot.example.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.giffing.wicket.spring.boot.example.web.SpringBootWebPackageIdentifier;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

//TODO move to test
@WicketSpringBootApplication
@EnableAutoConfiguration(exclude={
		DataSourceAutoConfiguration.class, 
		JpaRepositoriesAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class,
	})
@ComponentScan(basePackageClasses=SpringBootWebPackageIdentifier.class)
public class WicketWebApplicationConfig extends WicketBootWebApplication {

}
