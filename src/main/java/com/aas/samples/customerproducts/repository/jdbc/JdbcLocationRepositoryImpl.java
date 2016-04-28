package com.aas.samples.customerproducts.repository.jdbc;

import com.aas.samples.customerproducts.model.Location;
import com.aas.samples.customerproducts.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * A simple JDBC-based implementation of the {@link LocationRepository} 
 * interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JdbcLocationRepositoryImpl implements LocationRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcLocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Location findById(int id) throws DataAccessException {
        // Retrieve the customer with the specified id
        return this.jdbcTemplate.queryForObject(
            "SELECT l.id, l.name " 
          + "FROM locations l " 
          + "WHERE l.id = " + id,
          Location.class);
    }

}
