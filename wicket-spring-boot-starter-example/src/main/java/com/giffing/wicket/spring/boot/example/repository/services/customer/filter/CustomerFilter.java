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
	
	private String firstnameLike;
	
	private String lastnameLike;
	
	private boolean active;

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

	public String getFirstnameLike() {
		return firstnameLike;
	}

	public void setFirstnameLike(String firstnameLike) {
		this.firstnameLike = firstnameLike;
	}

	public String getLastnameLike() {
		return lastnameLike;
	}

	public void setLastnameLike(String lastnameLike) {
		this.lastnameLike = lastnameLike;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
