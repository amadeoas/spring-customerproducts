package com.aas.samples.customerproducts.repository.jdbc;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * A simple JDBC-based implementation of the {@link CustomerRepository} 
 * interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JdbcCustomerRepositoryImpl implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcCustomerRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Collection<Customer> findAll() throws DataAccessException {
        final Collection<Customer> customers = new ArrayList<>();

        // Retrieve the list of all customers
        customers.addAll(this.jdbcTemplate.query(
            "SELECT c.id, c.first_name, c.last_name, l.id, l.name " 
          + "FROM customer AS c, location AS l " 
          + "WHERE c.location_id = l.id " 
          + "ORDER BY c.first_name, c.last_name",
            BeanPropertyRowMapper.newInstance(Customer.class)));
        
        return customers;
    }
    
    @Override
    public Customer findById(int id) throws DataAccessException {
        // Retrieve the customer with the specified ID
        return this.jdbcTemplate.queryForObject(
            "SELECT c.id, c.first_name, c.last_name, l.id, l.name " 
          + "FROM customer AS c, location AS l " 
          + "WHERE c.id = " + id + " AND c.location_id = l.id ",
          Customer.class);
    }

}
