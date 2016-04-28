package com.aas.samples.customerproducts.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.repository.CatalogueRepository;

import org.springframework.stereotype.Repository;


/**
 * JPA implementation of the {@link CatalogueRepository} interface.
 *
 * @author Amadeo Asco
 */
@Repository
public class JpaCatalogueRepositoryImpl implements CatalogueRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public Collection<Product> findAll() {
        final Query query = this.em.createQuery(
        		"SELECT p " 
        	  + "FROM Product p "
        	  + "ORDER BY p.name");

        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Product> findByLocation(final int locationId) {
        final Query query = this.em.createQuery(
        		"SELECT p " 
        	  + "FROM Product p JOIN p.location l JOIN p.category c "
        	  + "WHERE l.id =:id OR l.id = 1 "
        	  + "ORDER BY c.name, p.name");

        query.setParameter("id", locationId);

        return query.getResultList();
    }

}
