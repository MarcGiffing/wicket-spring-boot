package com.giffing.wicket.spring.boot.example.repository.jpa.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly = true)
public class CustomSimpleJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomJpaSpecificationExecutor<T> {

	public CustomSimpleJpaRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public CustomSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public List<T> findAllBySpec(Specification<T> spec, long offset, long count, Sort sort) {
		TypedQuery<T> query = null;
		if(spec == null) {
			query = getQuery(Specification.where(null),sort);
		} else {
			query = getQuery(spec, sort);
		}
		return query
			.setFirstResult(Long.valueOf(offset).intValue())
			.setMaxResults(Long.valueOf(count).intValue())
			.getResultList();
	}

}
