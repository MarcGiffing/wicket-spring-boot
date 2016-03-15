package com.giffing.wicket.spring.boot.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.giffing.wicket.spring.boot.example.repository.jpa.support.CustomSimpleJpaRepository;
import com.giffing.wicket.spring.boot.example.repository.services.DefaultRepositoryService;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses={DefaultRepositoryService.class}, repositoryBaseClass = CustomSimpleJpaRepository.class)
public class WicketApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder()
			.sources(WicketApplication.class)
			.run(args);
	}

}
