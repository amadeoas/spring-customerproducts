package com.aas.samples.customerproducts.repository.jdbc;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.model.Location;
import com.aas.samples.customerproducts.repository.CustomerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
            "SELECT * " 
          + "FROM customers AS c, locations AS l " 
          + "WHERE c.location_id = l.id " 
          + "ORDER BY c.first_name, c.last_name",
          new ManyCustomersExtractor()));
        
        return customers;
    }
    
    @Override
    public Customer findById(int customerId) throws DataAccessException {
        // Retrieve the customer with the specified ID
        Customer customer = this.jdbcTemplate.query(
            "SELECT * " 
          + "FROM customers AS c, locations AS l " 
          + "WHERE c.id = " + customerId + " AND c.location_id = l.id",
          new OneCustomerExtractor());
          
        return customer;
    }
    
    
    /**
     * Builds a customer from a result
     * 
     * @param rs .
     * @return the created customer.
     * @throws SQLException when SQL query failed.
     */
    private Customer build(final ResultSet rs) throws SQLException {
    	final Customer customer = new Customer();
	   	final Location location = new Location(); //create course based on row

	   	customer.setId(rs.getInt(1));  
	   	customer.setFirstName(rs.getString(4));  
	   	customer.setLastName(rs.getString(5));

	   	location.setId(rs.getInt(2));
	   	location.setName(rs.getString(3));
	   	customer.setLocation(location);

	    return customer;
    }


    /**
     * Used to fetch a record from the database.
     * 
     * @author aasco
     */
    public class OneCustomerExtractor implements ResultSetExtractor<Customer> {
    	
    	@Override
    	public Customer extractData(final ResultSet rs) throws DataAccessException, 
    			SQLException {
    	    return rs.next() ? build(rs) : null;
    	}

    }
    

    /**
     * Used to fetch records from the database.
     * 
     * @author aasco
     */
    public class ManyCustomersExtractor implements ResultSetExtractor<List<Customer>> {
    	
    	@Override
    	public List<Customer> extractData(final ResultSet rs) throws DataAccessException, 
    			SQLException {
    		final List<Customer> customers = new ArrayList<>();  

    		while (rs.next()) {
    			customers.add(build(rs));
    		}

    	    return customers;
    	}

    }

}
