package com.aas.samples.customerproducts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.model.Subscription;
import com.aas.samples.customerproducts.model.SubscriptionProduct;
import com.aas.samples.customerproducts.repository.CustomerRepository;
import com.aas.samples.customerproducts.repository.SubscriptionProductRepository;


/**
 * Mostly used as a facade for all Subscription controllers
 * Also a placeholder for @Transactional and @Cacheable annotations.
 *
 * @author Amadeo Asco
 */
@Service
public class SubscriptionProductServiceImpl implements SubscriptionProductService {

    private CustomerRepository customerRepository;
    private SubscriptionProductRepository subscriptionRepository;

    
    @Autowired
    public SubscriptionProductServiceImpl(final CustomerRepository customerRepository, 
    		final SubscriptionProductRepository subscriptionRepository) {
    	this.customerRepository = customerRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

	@Override
	public Subscription findByCustomerId(int customerId) {
		final List<SubscriptionProduct> subscriptions 
				= this.subscriptionRepository.findByCustomerId(customerId);
		final Customer customer;
		
		if (subscriptions.size() > 0) {
			// No need to call for customer details
			customer = subscriptions.get(0).getCustomer();
		} else {
			customer = this.customerRepository.findById(customerId);
		}

		return new Subscription(customer, subscriptions);
	}


	@Override
	public void save(final Subscription subscription) 
			throws DataAccessException {
		this.subscriptionRepository.save(subscription.getCustomer().getId(), 
				subscription.getProducts());
	}

}
