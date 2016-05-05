package com.aas.samples.customerproducts.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.repository.CatalogueRepository;


/**
 * Spring Data JPA specialisation of the {@link CatalogueRepository} interface.
 *
 * @author Amadeo Asco
 */
public interface SpringDataCatalogueRepository extends CatalogueRepository, Repository<Product, Integer> {

    @Override
    @Query("SELECT p FROM Product p ORDER BY p.category.name, p.name")
    public Collection<Product> findAll();

    @Override
    @Query("SELECT p FROM Product p JOIN p.location l JOIN p.category c WHERE l.id = ?1 OR l.id = 1 ORDER BY c.name, p.name")
    public Collection<Product> findByLocation(int locationId);

}
