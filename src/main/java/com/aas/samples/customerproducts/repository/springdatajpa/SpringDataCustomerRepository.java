package com.aas.samples.customerproducts.repository.springdatajpa;

import java.util.Collection;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.repository.CustomerRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;


/**
 * Spring Data JPA specialisation of the {@link CustomerRepository} interface.
 *
 * @author Amadeo Asco
 */
public interface SpringDataCustomerRepository extends CustomerRepository, Repository<Customer, Integer> {

    @Override
    @Query("SELECT c FROM Customer c ORDER BY c.lastName, c.firstName")
    Collection<Customer> findAll() throws DataAccessException;

    @Override
    @Query("SELECT c FROM Customer c WHERE c.id = ?1 ORDER BY c.lastName, c.firstName")
    Customer findById(int customerId);

}
