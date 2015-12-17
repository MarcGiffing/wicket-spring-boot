package com.giffing.wicket.spring.boot.example.repository.services;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.giffing.wicket.spring.boot.example.repository.Filter;
import com.giffing.wicket.spring.boot.example.repository.FilterService;

public abstract class DefaultRepositoryService<MODEL, FILTER extends Filter> implements FilterService<MODEL, FILTER>{

	
	public Sort getSort(FILTER filter) {
		Sort sort = null;
		if(filter.sort() != null){
			Direction direction = filter.isAscending() ? Direction.ASC : Direction.DESC;
			sort = new Sort(direction, filter.sort().getFieldName());
		}
		return sort;
	}

}
