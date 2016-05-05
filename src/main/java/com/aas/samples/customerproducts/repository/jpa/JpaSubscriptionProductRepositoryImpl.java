package com.aas.samples.customerproducts.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aas.samples.customerproducts.model.SubscriptionProduct;
import com.aas.samples.customerproducts.repository.SubscriptionProductRepository;


/**
 * JPA implementation of the {@link SubscriptionProductRepository} interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JpaSubscriptionProductRepositoryImpl implements SubscriptionProductRepository {

    @PersistenceContext
    private EntityManager em;

    
	@SuppressWarnings("unchecked")
	@Override
	public List<SubscriptionProduct> findByCustomerId(int customerId) throws DataAccessException {
		final Query query = this.em.createQuery(
    			"SELECT sp " 
    		  + "FROM SubscriptionProduct sp "
    		  + "WHERE sp.customer.id = " + customerId);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(final int customerId, final List<SubscriptionProduct> subscriptions) 
			throws DataAccessException {
		Query query;

		// Delete all current subscriptions
		query = this.em.createQuery(
				"DELETE " 
				+ "FROM SubscriptionProduct AS sp "
		    	+ "WHERE sp.customer.id = " + customerId);

		query.executeUpdate();

		if (subscriptions == null || subscriptions.size() == 0) {
			return;
		}
		
		for (final SubscriptionProduct sp : subscriptions) {
			this.em.merge(sp);
		}
	}

}
