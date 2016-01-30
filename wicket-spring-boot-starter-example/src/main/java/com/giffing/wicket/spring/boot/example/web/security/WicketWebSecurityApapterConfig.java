package com.giffing.wicket.spring.boot.example.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Default Spring Boot Wicket security getting started configuration. Its only
 * active if there is not other {@link WebSecurityConfigurerAdapter} present.
 * 
 * Holds hard coded users which should only be used to get started
 * 
 * @author Marc Giffing
 *
 */
@Configuration
public class WicketWebSecurityApapterConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/**").permitAll()
			.and()
			.logout()
			.permitAll();
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("admin").authorities("USER", "ADMIN");
	}

}
