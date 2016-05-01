package com.aas.samples.customerproducts.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aas.samples.customerproducts.model.SubscriptionProduct;
import com.aas.samples.customerproducts.repository.SubscriptionProductRepository;


/**
 * Spring Data JPA specialisation of the {@link SubscriptionProductRepository} interface.
 *
 * @author Amadeo Asco
 */
public interface SpringDataSubscriptionProductRepositoryImpl extends SubscriptionProductRepository {

    @Override
    @Query("SELECT s FROM subscriptions s WHERE s.customer_id :=id")
	public List<SubscriptionProduct> findByCustomerId(@Param("customer_id") int id) 
			throws DataAccessException;

    // TODO: 
    @Override
    @Query("INSERT INTO subscription_products (id, customer_id, product_id) VALUES (?, ?, ?)")
	public void save(int custom_id, List<SubscriptionProduct> subscriptions) throws DataAccessException;

}
