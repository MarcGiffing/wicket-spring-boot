package com.giffing.wicket.spring.boot.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

@SpringBootApplication
@Configuration(value = "wicketBootWebApplication")
@ComponentScan
public class WicketApplication extends WicketBootWebApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder()
			.sources(WicketApplication.class)
			.run(args);
	}

}
