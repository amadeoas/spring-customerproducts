package com.aas.samples.customerproducts.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.repository.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Mostly used as a facade for all CustomerProduct controllers
 * Also a placeholder for @Transactional and @Cacheable annotations.
 *
 * @author Amadeo Asco
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    
    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Customer> getAllCustomers() throws DataAccessException {
    	return this.customerRepository.findAll();
    }

}
