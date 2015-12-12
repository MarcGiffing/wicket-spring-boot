package com.giffing.wicket.spring.boot.example.repository.services.customer.filter;

import com.giffing.wicket.spring.boot.example.repository.Sort;

public enum CustomerSort implements Sort{
	ID("id"),
	USERNAME("username");
	
	private String sortName;
	
	CustomerSort(String sortName){
		this.sortName = sortName;
	}

	@Override
	public String getSortName() {
		return this.sortName;
	}
	
}
