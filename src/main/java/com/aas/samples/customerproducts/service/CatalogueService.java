package com.aas.samples.customerproducts.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Product;


/**
 * Mostly used as a facade so all controllers have a single point of entry.
 *
 * @author Amadeo Asco
 */
public interface CatalogueService {

	/**
	 * @return the list of all the products.
	 * @throws DataAccessException when it has been an access problem.
	 */
    Collection<Product> getAllProducts() throws DataAccessException;

	/**
	 * Looks for all the valid products for the specified location.
	 * 
	 * @param locationId the ID of the location of interest.
	 * @return the list of all the valid products for the specified location.
	 * @throws DataAccessException when it has been an access problem.
	 */
    Collection<Product> findProducts(int locationId) throws DataAccessException;

}
