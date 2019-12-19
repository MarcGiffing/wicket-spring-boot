package com.giffing.wicket.spring.boot.example.repository.services.customer;


import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.model.Customer_;
import com.giffing.wicket.spring.boot.example.repository.services.DefaultRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.specs.CustomerSpecs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class CustomerRepositoryServiceImpl extends DefaultRepositoryService<Customer, Long, CustomerFilter> implements CustomerRepositoryService {

	private final CustomerRepository customerRepository;

	@Resource
	private EntityManager em;
	
	public CustomerRepositoryServiceImpl(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	@Override
	public List<Customer> findAll(Long page, Long count, CustomerFilter filter) {
		PageRequest pageRequest = PageRequest.of(page.intValue(), count.intValue(), getSort(filter));
		return customerRepository.findAll(filter(filter), pageRequest).toList();
	}

	@Override
	public long count(CustomerFilter filter) {
		return customerRepository.count(filter(filter));
	}
	
	private Specification<Customer> filter(CustomerFilter filter) {
		List<Specification<Customer>> specs = new ArrayList<>();
		
		if(filter.getId() != null){
			specs.add(CustomerSpecs.hasId(filter.getId()));
		}
		
		if(isNotEmpty(filter.getUsername())){
			specs.add(CustomerSpecs.hasUsername(filter.getUsername()));
		}
		
		if(isNotEmpty(filter.getUsernameLike())){
			specs.add(CustomerSpecs.hasUsernameLike(filter.getUsernameLike()));
		}
		
		if(isNotEmpty(filter.getFirstnameLike())){
			specs.add(CustomerSpecs.hasFirstnameLike(filter.getFirstnameLike()));
		}
		
		if(isNotEmpty(filter.getLastnameLike())){
			specs.add(CustomerSpecs.hasLastnameLike(filter.getLastnameLike()));
		}
		
		if(filter.isActive()){
			specs.add(CustomerSpecs.hasActive(filter.isActive()));
		}
		
		Specification<Customer> spec = null;
		for (Specification<Customer> specification : specs) {
			if(spec == null){
				spec = Specification.where(specification);
			}else {
				spec = spec.and(specification);
			}
		}
		
		return spec;
	}
	
	boolean isNotEmpty(String toCheck){
		return toCheck != null && toCheck.length() > 0;
	}


	@Override
	public Customer findById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<String> findUsernames(int count, String usernamePart) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<Customer> customerRoot = query.from(Customer.class);
		
		query = query.select(customerRoot.get(Customer_.username));
		
		Predicate predicate = CustomerSpecs.hasUsernameLike(usernamePart).toPredicate(customerRoot, query, builder);
		query = query.where(predicate);
		
		TypedQuery<String> createQuery = em.createQuery(query);
		createQuery.setMaxResults(count);
		return createQuery.getResultList();
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public boolean usernameExists(String username) {
		return customerRepository.countByUsernameIgnoreCase(username) >= 1;
	}

	@Override
	public Customer findByUsername(String username) {
		return customerRepository.findByUsernameIgnoreCase(username);
	}


	
}
