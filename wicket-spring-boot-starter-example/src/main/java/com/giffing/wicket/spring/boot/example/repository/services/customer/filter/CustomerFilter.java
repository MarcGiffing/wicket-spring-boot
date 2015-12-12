package com.giffing.wicket.spring.boot.example.repository.services.customer.filter;

import com.giffing.wicket.spring.boot.example.repository.Filter;
import com.giffing.wicket.spring.boot.example.repository.Sort;

public class CustomerFilter implements Filter {
	
	private Long id;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Sort sort() {
		return new CustomerSort();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
