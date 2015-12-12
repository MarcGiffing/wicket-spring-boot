package com.giffing.wicket.spring.boot.example;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

@WicketSpringBootApplication
@ComponentScan(basePackages="com.giffing.wicket.spring.boot.example")
public class WicketApplication extends WicketBootWebApplication {
	
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder()
			.sources(WicketApplication.class)
			.run(args);
	}

}
