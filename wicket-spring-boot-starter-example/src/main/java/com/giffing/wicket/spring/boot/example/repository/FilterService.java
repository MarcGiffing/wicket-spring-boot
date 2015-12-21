package com.giffing.wicket.spring.boot.example.repository;

import java.util.List;

public interface FilterService<MODEL, ID, FILTER_MODEL extends Filter> {
	
	MODEL findById(ID id);
	
	List<MODEL> findAll(Long page, Long count, FILTER_MODEL filter);
	
	long count(FILTER_MODEL filter);
	
}
