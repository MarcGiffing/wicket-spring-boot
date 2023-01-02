package com.giffing.wicket.spring.boot.example.web.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Default Spring Boot Wicket security getting started configuration. Its only
 * active if there is not other {@link SecurityFilterChain} bean is present.
 *
 * Holds hard coded users which should only be used to get started
 * 
 * @author Marc Giffing
 *
 */
@Configuration
@EnableWebSecurity
public class WicketWebSecurityAdapterConfig {

	@ConditionalOnMissingBean
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }
    
	@ConditionalOnMissingBean
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests().requestMatchers("/**").permitAll()
			.and().logout().permitAll();
		http.headers().frameOptions().disable();
		return http.build();
	}
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	}

	@Bean
	//TODO Add Wicket Issue - problem with semicolon in wicket websocket url. Allow semicolon.
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall fw = new StrictHttpFirewall();
		fw.setAllowSemicolon(true);
	    return fw;
	}

	@ConditionalOnMissingBean
	@Bean
	public UserDetailsService userDetailsService(final PasswordEncoder passwordEncoder) {
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(
	    		User.withUsername("admin")
	    		 .password(passwordEncoder.encode("admin"))
	    		 .authorities("USER", "ADMIN")
	    		 .build());
	    manager.createUser(
	    		User.withUsername("customer")
	    		 .password(passwordEncoder.encode("customer"))
	    		 .authorities("USER", "ADMIN")
	    		 .build());
	    return manager;
	}
	
	//@Bean
	//public WicketSessionResolver springSecurityWicketSessionResolver() {
	//	return new SpringSecurityWicketSessionResolver();
	//}

}
