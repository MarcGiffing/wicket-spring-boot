package com.giffing.wicket.spring.boot.example.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Component;

@Component
@Primary
@Order(99)
public class WicketWebSecurityApapterConfigTest extends WicketWebSecurityApapterConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("test").password("test123").authorities("USER", "ADMIN");
	}
	
}
