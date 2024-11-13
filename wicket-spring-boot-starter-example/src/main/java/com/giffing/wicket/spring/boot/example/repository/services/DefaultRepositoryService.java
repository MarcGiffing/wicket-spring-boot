package com.giffing.wicket.spring.boot.example.repository.services;

import com.giffing.wicket.spring.boot.example.repository.Filter;
import com.giffing.wicket.spring.boot.example.repository.FilterService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public abstract class DefaultRepositoryService<MODEL, ID, FILTER extends Filter> implements FilterService<MODEL, ID, FILTER> {

    public Sort getSort(FILTER filter) {
        var sort = Sort.unsorted();
        if (filter.sort() != null) {
            Direction direction = filter.isAscending() ? Direction.ASC : Direction.DESC;
            sort = Sort.by(direction, filter.sort().getFieldName());
        }
        return sort;
    }

}
