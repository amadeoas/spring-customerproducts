package com.aas.samples.customerproducts.service;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Subscription;


/**
 * Mostly used as a facade so all controllers have a single point of entry.
 *
 * @author Amadeo Asco
 */
public interface SubscriptionProductService {

	/**
	 * @param customerId the customer ID.
	 * @return the subscriptions for the specified customer.
	 * @throws DataAccessException when it has been an access problem.
	 */
	Subscription findByCustomerId(int customerId) throws DataAccessException;
	
	/**
	 * Saves the specified subscription.
	 * 
	 * @param subscription the subscriptions.
	 * @throws DataAccessException when it has been an access problem.
	 */
	void save(Subscription subscription) throws DataAccessException;

}
