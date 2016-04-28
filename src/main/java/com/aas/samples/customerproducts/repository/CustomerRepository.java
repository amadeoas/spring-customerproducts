package com.aas.samples.customerproducts.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Customer;


/**
 * Repository class for <code>Customer</code> domain objects. All method names 
 * are compliant with Spring Data naming conventions so this interface can 
 * easily be extended for Spring Data, see here: 
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Amadeo Asco
 */
public interface CustomerRepository {

    /**
     * Retrieves all <code>Customer</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Customer</code>s.
     */
    Collection<Customer> findAll() throws DataAccessException;

    /**
     * Retrieves a <code>Customer</code> from the data store by id.
     *
     * @param id the id to search for.
     * @return the <code>Customer</code> if found.
     */
	Customer findById(int id) throws DataAccessException;

}
