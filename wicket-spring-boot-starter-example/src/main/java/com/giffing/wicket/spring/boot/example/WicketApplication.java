package com.giffing.wicket.spring.boot.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.giffing.wicket.spring.boot.example.repository.services.DefaultRepositoryService;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses={DefaultRepositoryService.class})
public class WicketApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
			.sources(WicketApplication.class)
			.run(args);
	}
	
}
