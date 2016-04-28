package com.aas.samples.customerproducts.service;

import org.springframework.dao.DataAccessException;


/**
 * Mostly used as a facade so all controllers have a single point of entry.
 *
 * @author Amadeo Asco
 */
public interface CustomerLocationService {

    /**
     * @return the locations ID associated with the specified customer.
	 * @throws DataAccessException when it has been an access problem.
     */
    int findLocation(int customerId) throws DataAccessException;

}
