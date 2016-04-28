package com.aas.samples.customerproducts.repository.springdatajpa;

import java.util.Collection;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.repository.CustomerRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA specialization of the {@link CustomerRepository} interface.
 *
 * @author Amadeo Asco
 */
public interface SpringDataCustomerRepository extends CustomerRepository, Repository<Customer, Integer> {

    @Override
    @Query("SELECT c FROM customers c ORDER BY c.name")
    Collection<Customer> findAll() throws DataAccessException;

    @Override
    @Query("SELECT c FROM customers c WHERE c.id :=id ORDER BY c.name")
    Customer findById(@Param("id") int id);

}
