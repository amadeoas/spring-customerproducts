package com.aas.samples.customerproducts.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.repository.CustomerRepository;

import org.springframework.stereotype.Repository;


/**
 * JPA implementation of the {@link CustomerRepository} interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JpaCustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
//    @Cacheable(value = "customers")
    @SuppressWarnings("unchecked")
    public Collection<Customer> findAll() {
    	final Query query = this.em.createQuery(
    			"SELECT c " 
    		  + "FROM Customer c " 
    		  + "ORDER BY c.lastName, c.firstName");
    	
    	return query.getResultList();
    }

	@Override
	public Customer findById(int customerId) throws DataAccessException {
		return this.em.find(Customer.class, customerId);
	}

}
