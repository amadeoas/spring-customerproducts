package com.aas.samples.customerproducts.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Product;


/**
 * Repository class for <code>Product</code> domain objects. All method names 
 * are compliant with Spring Data naming conventions so this interface can 
 * easily be extended for Spring Data, see here: 
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Amadeo Asco
 */
public interface CatalogueRepository {

    /**
     * Retrieves all <code>Product</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Product</code>s.
     */
    Collection<Product> findAll() throws DataAccessException;

    /**
     * Retrieves <code>Products</code>s from the data store by the location ID,.
     *
     * @param locationId the ID for a location.
     * @return a <code>Collection</code> of matching <code>Product</code>s (or 
     * 			an empty <code>Collection</code> if none found).
     */
    Collection<Product> findByLocation(final int locationId) throws DataAccessException;

}
