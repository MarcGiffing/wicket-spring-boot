package com.giffing.wicket.spring.boot.example.repository.services.customer.filter;

import com.giffing.wicket.spring.boot.example.repository.DefaultFilter;

public class CustomerFilter extends DefaultFilter {
	
	private Long id;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
