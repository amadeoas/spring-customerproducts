package com.aas.samples.customerproducts.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	private static final String SQL_DELETE_ALL_SP = "DELETE sp " 
              + "FROM subscription_products sp " 
              + "WHERE sp.customer_id = ";

	private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcSubscriptionProductRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubscriptionProduct> findByCustomerId(int customerId) 
			throws DataAccessException {
		return this.jdbcTemplate.queryForObject(
                "SELECT s " 
              + "FROM subscription_products sp " 
              + "WHERE sp.customer_id = " + customerId,
              List.class);
	}

	@Override
	public void save(final int customerId, final List<SubscriptionProduct> subscriptions) 
			throws DataAccessException {
		// Remove all. TODO: not very efficient
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

}
