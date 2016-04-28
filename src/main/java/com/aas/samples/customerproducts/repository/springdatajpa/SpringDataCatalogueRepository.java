package com.aas.samples.customerproducts.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.repository.CatalogueRepository;


/**
 * Spring Data JPA specialization of the {@link CatalogueRepository} interface.
 *
 * @author Amadeo Asco
 */
public interface SpringDataCatalogueRepository extends CatalogueRepository, Repository<Product, Integer> {

    @Override
    @Query("SELECT DISTINCT p, c, l FROM products p, categories c, locations l ORDER c.name, p.name")
    public Collection<Product> findAll();

    @Override
    @Query("SELECT p, c, l FROM products p, categories c, locations l WHERE p.location_id =:id ORDER c.name, p.name")
    public Collection<Product> findByLocation(@Param("id") int locationId);

}
