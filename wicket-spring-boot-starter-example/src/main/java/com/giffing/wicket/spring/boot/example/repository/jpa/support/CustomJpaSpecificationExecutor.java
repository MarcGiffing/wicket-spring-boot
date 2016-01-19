package com.giffing.wicket.spring.boot.example.repository.jpa.support;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Custom repository which extends the {@link JpaSpecificationExecutor}
 * 
 * @author Marc Giffing
 *
 */
@Repository
public interface CustomJpaSpecificationExecutor<T> extends JpaSpecificationExecutor<T> {

	List<T> findAllBySpec(Specification<T> spec, long offset, long count, Sort sort);
	
}
