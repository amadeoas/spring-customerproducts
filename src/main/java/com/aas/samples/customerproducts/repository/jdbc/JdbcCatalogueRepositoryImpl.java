package com.aas.samples.customerproducts.repository.jdbc;

import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.repository.CatalogueRepository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * A simple JDBC-based implementation of the {@link CatalogueRepository} 
 * interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JdbcCatalogueRepositoryImpl implements CatalogueRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcCatalogueRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Collection<Product> findAll() throws DataAccessException {
        final Collection<Product> products = new ArrayList<>();

        // Retrieve the list of all products
        products.addAll(this.jdbcTemplate.query(
            "SELECT p.id, p.name, c, l " 
          + "FROM products p, category c, locations l " 
          + "WHERE p.category_id = c.id AND p.location_id = l.id " 
          + "ORDER BY c.name, p.name",
            BeanPropertyRowMapper.newInstance(Product.class)));
        
        return products;
    }
    
    @Override
    public Collection<Product> findByLocation(int locationId) throws DataAccessException {
        final Collection<Product> products = new ArrayList<>();

        // Retrieve the list of all products for the specified location
        products.addAll(this.jdbcTemplate.query(
            "SELECT p.id, p.name, c, l " 
          + "FROM products p, category c, locations l " 
          + "WHERE p.location_id = " + locationId + " AND p.category_id = c.id AND p.location_id = l.id " 
          + "ORDER BY c.name, p.name",
            BeanPropertyRowMapper.newInstance(Product.class)));
        
        return products;
    }

}