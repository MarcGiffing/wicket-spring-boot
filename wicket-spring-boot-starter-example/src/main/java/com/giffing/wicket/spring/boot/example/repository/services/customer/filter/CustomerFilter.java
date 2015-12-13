package com.giffing.wicket.spring.boot.example.repository.services.customer.filter;

import com.giffing.wicket.spring.boot.example.repository.DefaultFilter;

public class CustomerFilter extends DefaultFilter {
	
	private Long id;
	
	/**
	 * Filtering for the exact user name
	 */
	private String username;
	
	/**
	 * Filtering by lowercase username with wildcards at the start and the end.
	 */
	private String usernameLike;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsernameLike() {
		return usernameLike;
	}

	public void setUsernameLike(String usernameLike) {
		this.usernameLike = usernameLike;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
