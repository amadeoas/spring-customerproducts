package com.aas.samples.customerproducts.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.aas.samples.customerproducts.model.SubscriptionProduct;
import com.aas.samples.customerproducts.repository.SubscriptionProductRepository;


/**
 * Spring Data JPA specialisation of the {@link SubscriptionProductRepository} interface.
 *
 * @author Amadeo Asco
 */
public interface SpringDataSubscriptionProductRepositoryImpl 
		extends SubscriptionProductRepository, Repository<SubscriptionProduct, Integer> {

    @Override
    @Query("SELECT sp FROM SubscriptionProduct sp WHERE sp.customer.id = ?1")
	public List<SubscriptionProduct> findByCustomerId(int customerId) 
			throws DataAccessException;

    // TODO: 
    @Override
    @Query(value = "INSERT INTO subscription_products(id, customer_id, product_id) VALUES (:sp.id, :customerId, :sp.profuct.id)", nativeQuery = true)
	public void save(@Param("customerId") int customerId, @Param("sp") List<SubscriptionProduct> subscriptions) 
			throws DataAccessException;

}
