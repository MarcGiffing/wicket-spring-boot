package com.giffing.wicket.spring.boot.example.repository.services.customer;


import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.model.Customer_;
import com.giffing.wicket.spring.boot.example.repository.services.DefaultRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.specs.CustomerSpecs;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryServiceImpl extends DefaultRepositoryService<Customer, Long, CustomerFilter> implements CustomerRepositoryService {

    private CustomerRepository customerRepository;

    @Resource
    private EntityManager em;

    public CustomerRepositoryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll(Long page, Long count, CustomerFilter filter) {
        PageRequest pr = PageRequest.of(page.intValue(), count.intValue(), getSort(filter));
        return customerRepository.findAll(filter(filter), pr).getContent();
    }

    @Override
    public long count(CustomerFilter filter) {
        return customerRepository.count(filter(filter));
    }

    private Specification<Customer> filter(CustomerFilter filter) {
        List<Specification<Customer>> specs = new ArrayList<>();

        if (filter.getId() != null) {
            specs.add(CustomerSpecs.hasId(filter.getId()));
        }

        if (isNotEmpty(filter.getUsername())) {
            specs.add(CustomerSpecs.hasUsername(filter.getUsername()));
        }

        if (isNotEmpty(filter.getUsernameLike())) {
            specs.add(CustomerSpecs.hasUsernameLike(filter.getUsernameLike()));
        }

        if (isNotEmpty(filter.getFirstnameLike())) {
            specs.add(CustomerSpecs.hasFirstnameLike(filter.getFirstnameLike()));
        }

        if (isNotEmpty(filter.getLastnameLike())) {
            specs.add(CustomerSpecs.hasLastnameLike(filter.getLastnameLike()));
        }

        if (filter.isActive()) {
            specs.add(CustomerSpecs.hasActive(filter.isActive()));
        }

        Specification<Customer> spec = null;
        for (Specification<Customer> specification : specs) {
            if (spec == null) {
                spec = Specification.where(specification);
            } else {
                spec = spec.and(specification);
            }
        }

        return spec;
    }

    boolean isNotEmpty(String toCheck) {
        if (toCheck != null && toCheck.length() > 0) {
            return true;
        }

        return false;
    }


    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.isPresent() ? customer.get() : null;
    }

    @Override
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
