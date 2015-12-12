package com.giffing.wicket.spring.boot.example.repository;

import java.util.List;

public interface FilterService<T, F extends Filter> {

	List<T> findAll(Long page, Long count, F filter);
	
	long count(F filter);
	
}
