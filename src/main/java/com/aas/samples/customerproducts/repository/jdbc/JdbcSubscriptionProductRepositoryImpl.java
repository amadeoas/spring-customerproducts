package com.aas.samples.customerproducts.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.aas.samples.customerproducts.model.Category;
import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.model.Location;
import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.model.SubscriptionProduct;
import com.aas.samples.customerproducts.repository.SubscriptionProductRepository;


/**
 * A simple JDBC-based implementation of the {@link SubscriptionProductRepository} 
 * interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JdbcSubscriptionProductRepositoryImpl implements SubscriptionProductRepository {

	private static final String SQL_INSERT_SP = "INSERT INTO subscription_products " 
			+ "(id, customer_id, product_id) VALUES (?, 1, 2)";
	private static final String SQL_DELETE_ALL_SP = "DELETE " 
              + "FROM subscription_products AS sp " 
              + "WHERE sp.customer_id = ";

	private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcSubscriptionProductRepositoryImpl(final DataSource dataSource, 
    		final JdbcTemplate jdbcTemplate) {
    	this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<SubscriptionProduct> findByCustomerId(int customerId) 
			throws DataAccessException {
		return this.jdbcTemplate.query(
                "SELECT sp.id, sp.created, c.id, c.first_name, c.last_name, l.id, l.name, p.id, p.name, ca.id, ca.name " 
              + "FROM subscription_products AS sp, customers AS c, locations AS l, products AS p, categories AS ca " 
              + "WHERE sp.customer_id = " + customerId + " AND sp.customer_id = c.id AND c.location_id = l.id AND sp.product_id = p.id AND p.category_id = ca.id",
              new ManySubscriptionProductExtractor());
	}

	@Override
	public void save(final int customerId, final List<SubscriptionProduct> subscriptions) 
			throws DataAccessException {
		// Remove all as new list may not have some that currently are in the database.
		// TODO: not very efficient
		final String SQL_DELETE_ALL = SQL_DELETE_ALL_SP + customerId;
		final Object[] aParams = {}; // // define query arguments
		final int[] aiTypes = {}; // define SQL types of the arguments

		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
		this.jdbcTemplate.update(SQL_DELETE_ALL, aParams, aiTypes);
		
		if (subscriptions == null || subscriptions.size() == 0) {
			return;
		}

		this.jdbcTemplate.batchUpdate(SQL_INSERT_SP, 
				new BatchPreparedStatementSetter() {

		    @Override
		    public void setValues(final PreparedStatement ps, int i) throws SQLException {
		    	final SubscriptionProduct sp = subscriptions.get(i);

		    	ps.setInt(1, sp.getCustomer().getId());
		        ps.setInt(2, sp.getProduct().getId());
		    }

		    @Override
		    public int getBatchSize() {
		        return subscriptions.size();
		    }

		});
	}

    
    /**
     * Used to fetch subscription from the database.
     * 
     * @author aasco
     */
    public class ManySubscriptionProductExtractor implements ResultSetExtractor<List<SubscriptionProduct>> {
    	
    	@Override
    	public List<SubscriptionProduct> extractData(final ResultSet rs) throws DataAccessException, 
    			SQLException {
    		final List<SubscriptionProduct> list = new ArrayList<>();
    		Customer customer = null;

    		while (rs.next()) {
    			final SubscriptionProduct sp = build(rs, customer);
    			
    			if (customer == null) {
    				customer = sp.getCustomer();
    			}

    			list.add(sp);
    		}

    	    return list;
    	}
    	
    	private SubscriptionProduct build(final ResultSet rs, Customer customer) 
    			throws SQLException {
    		final SubscriptionProduct sp = new SubscriptionProduct();
			final Product product = new Product();
			final Category category;

			sp.setId(rs.getInt(1));
			sp.setCreated(rs.getTimestamp(2));
			
			if (customer == null) {
				final Location location = new Location();

				customer = new Customer();
				customer.setId(rs.getInt(3));
				customer.setFirstName(rs.getString(4));
				customer.setLastName(rs.getString(5));

				location.setId(rs.getInt(6));
				location.setName(rs.getString(7));
				customer.setLocation(location);
			}
				
			sp.setCustomer(customer);

			category = new Category();

			product.setId(rs.getInt(8));
			product.setName(rs.getString(9));
			product.setLocation(customer.getLocation());
			
			category.setId(rs.getInt(10));
			category.setName(rs.getString(11));
			product.setCategory(category);
			
			sp.setProduct(product);
			
			return sp;
    	}

    }

}
