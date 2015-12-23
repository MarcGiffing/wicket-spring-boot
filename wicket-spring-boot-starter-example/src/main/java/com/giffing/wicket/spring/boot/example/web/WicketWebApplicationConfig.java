package com.giffing.wicket.spring.boot.example.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

//TODO move to test
@WicketSpringBootApplication
@EnableAutoConfiguration(exclude={
		DataSourceAutoConfiguration.class, 
		JpaRepositoriesAutoConfiguration.class, 
		JpaBaseConfiguration.class,
		HibernateJpaAutoConfiguration.class,
	})
public class WicketWebApplicationConfig extends WicketBootWebApplication {

}
