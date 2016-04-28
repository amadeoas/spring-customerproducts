package com.aas.samples.customerproducts.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Customer;


/**
 * Mostly used as a facade so all controllers have a single point of entry.
 *
 * @author Amadeo Asco
 */
public interface CustomerService {

	/**
	 * @return all the customer details.
	 * @throws DataAccessException when it has been an access problem.
	 */
	Collection<Customer> getAllCustomers() throws DataAccessException;

}
