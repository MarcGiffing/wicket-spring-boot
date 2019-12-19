package com.giffing.wicket.spring.boot.example.repository.services.customer.specs;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.model.Customer_;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecs {

	public static Specification<Customer> hasId(final Long id) {
		return (root, query, builder) -> builder.equal(root.get(Customer_.id), id);
	}

	public static Specification<Customer> hasUsername(final String username) {
		return (root, query, builder) -> builder.equal(builder.lower(root.get(Customer_.username)), username.toLowerCase());
	}

	public static Specification<Customer> hasUsernameLike(final String username) {
		return (root, query, builder) -> builder.like(builder.lower(root.get(Customer_.username)), "%" + username.toLowerCase() + "%");
	}

	public static Specification<Customer> hasFirstnameLike(final String firstname) {
		return (root, query, builder) -> builder.like(builder.lower(root.get(Customer_.firstname)), "%" + firstname.toLowerCase() + "%");
	}

	public static Specification<Customer> hasLastnameLike(final String lastname) {
		return (root, query, builder) -> builder.like(builder.lower(root.get(Customer_.lastname)), "%" + lastname.toLowerCase() + "%");
	}

	public static Specification<Customer> hasActive(final boolean active) {
		return (root, query, builder) -> builder.equal(root.get(Customer_.active), active);
	}
}
