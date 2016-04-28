package com.aas.samples.customerproducts.repository;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Location;


/**
 * Repository class for <code>Location</code> domain objects. All method names 
 * are compliant with Spring Data naming conventions so this interface can 
 * easily be extended for Spring Data, see here: 
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Amadeo Asco
 */
public interface LocationRepository {

    /**
     * Retrieves a <code>Location</code> from the data store by id.
     *
     * @param id the id to search for.
     * @return the <code>Location</code> if found location is not found.
     */
    Location findById(int id) throws DataAccessException;

}
