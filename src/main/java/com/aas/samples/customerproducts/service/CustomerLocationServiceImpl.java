package com.aas.samples.customerproducts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.repository.CustomerRepository;


/**
 * Mostly used as a facade for all CustomerProduct controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Amadeo Asco
 */
@Service
public class CustomerLocationServiceImpl implements CustomerLocationService {

    private CustomerRepository customerRepository;

    
    @Autowired
    public CustomerLocationServiceImpl(final CustomerRepository customerRepository) {
    	this.customerRepository = customerRepository;
    }

	@Override
	public int findLocation(int customerId) throws DataAccessException {
		final Customer customer = this.customerRepository.findById(customerId);

		if (customer == null) {
			// TODO: throw exception here as the web app will show error
		}

		return customer.getLocation().getId();
	}

}
