package com.aas.samples.customerproducts.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.SubscriptionProduct;


/**
 * Repository class for <code>Subscription</code> domain objects. All method names 
 * are compliant with Spring Data naming conventions so this interface can 
 * easily be extended for Spring Data, see here: 
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Amadeo Asco
 */
public interface SubscriptionProductRepository {

    /**
     * Retrieves the list of <code>SubscriptionProduct</code> from the data 
     * store for the customer's ID specified.
     *
     * @param customerId the customer's ID to search for.
     * @return the list of <code>SubscriptionProduct</code> for the specified 
     * 			customer.
     */
	List<SubscriptionProduct> findByCustomerId(int customerId) throws DataAccessException;


    /**
     * Saves the specified <code>Subscription</code>.
     *
     * @param customerId the customer's ID. It is needed in the case of the 
     * 			list is empty so we need to delete all entries for that 
     * 			customer.
     * @param subscription the subscription.
     */
    void save(int customerId, List<SubscriptionProduct> subscriptions) 
    		throws DataAccessException;

}
