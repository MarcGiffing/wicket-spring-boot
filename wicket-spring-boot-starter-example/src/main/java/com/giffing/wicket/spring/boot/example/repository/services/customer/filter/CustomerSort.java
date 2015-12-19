package com.giffing.wicket.spring.boot.example.repository.services.customer.filter;

import com.giffing.wicket.spring.boot.example.model.Customer_;
import com.giffing.wicket.spring.boot.example.repository.Sort;

public enum CustomerSort implements Sort{
	ID("id"),
	USERNAME("username"),
	FIRSTNAME("firstname"),
	LASTNAME("lastname"),
	ACTIVE("active");

	private String sortName;
	
	CustomerSort(String sortName){
		this.sortName = sortName;
	}

	@Override
	public String getFieldName() {
		return this.sortName;
	}
	
}
