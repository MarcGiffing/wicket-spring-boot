package com.giffing.wicket.spring.boot.example.repository;

import java.util.List;

public interface FilterService<T, F extends Filter> {
	
	T findById(Long object);
	
	List<T> findAll(Long page, Long count, F filter);
	
	long count(F filter);
	
}
