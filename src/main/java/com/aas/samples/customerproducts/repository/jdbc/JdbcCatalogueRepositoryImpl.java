package com.aas.samples.customerproducts.repository.jdbc;

import com.aas.samples.customerproducts.model.Category;
import com.aas.samples.customerproducts.model.Location;
import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.repository.CatalogueRepository;

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

    @Override
    public Collection<Product> findAll() throws DataAccessException {
        final Collection<Product> products = new ArrayList<>();

        // Retrieve the list of all products
        products.addAll(this.jdbcTemplate.query(
            "SELECT p.id, p.name, l.id, l.name, c.id, c.name " 
          + "FROM products AS p, categories AS c, locations AS l " 
          + "WHERE p.category_id = c.id AND p.location_id = l.id " 
          + "ORDER BY c.name, p.name",
          	new ManyProductsExtractor()));
        
        return products;
    }
    
    @Override
    public Collection<Product> findByLocation(int locationId) throws DataAccessException {
        final Collection<Product> products = new ArrayList<>();

        // Retrieve the list of all products for the specified location
        products.addAll(this.jdbcTemplate.query(
        	"SELECT p.id, p.name, l.id, l.name, c.id, c.name " 
          + "FROM products AS p, categories AS c, locations AS l " 
          + "WHERE (l.id = " + locationId + " OR l.id = 1) AND p.category_id = c.id AND p.location_id = l.id " 
          + "ORDER BY c.name, p.name",
            new ManyProductsExtractor()));
        
        return products;
    }
    

    /**
     * Used to fetch products from the database.
     * 
     * @author aasco
     */
    public class ManyProductsExtractor implements ResultSetExtractor<List<Product>> {
    	
    	@Override
    	public List<Product> extractData(final ResultSet rs) throws DataAccessException, 
    			SQLException {
    		final List<Product> products = new ArrayList<>();

    		while (rs.next()) {
    			final Product product = new Product();
    			final Category category = new Category();
    			final Location location = new Location();

    			product.setId(rs.getInt(1));
    			product.setName(rs.getString(2));
    			
    			location.setId(rs.getInt(3));
    			location.setName(rs.getString(4));
    			product.setLocation(location);
    			
    			category.setId(rs.getInt(5));
    			category.setName(rs.getString(6));
    			product.setCategory(category);
    			
    			products.add(product);
    		}

    	    return products;
    	}

    }

}
