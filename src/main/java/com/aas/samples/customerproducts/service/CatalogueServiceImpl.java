package com.aas.samples.customerproducts.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.repository.CatalogueRepository;


/**
 * Mostly used as a facade for all CustomerProduct controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Amadeo Asco
 */
@Service
public class CatalogueServiceImpl implements CatalogueService {

    private CatalogueRepository catalogueRepository;


    @Autowired
    public CatalogueServiceImpl(final CatalogueRepository catalogueRepository) {
    	this.catalogueRepository = catalogueRepository;
    }

    @Override
    public Collection<Product> getAllProducts() throws DataAccessException {
    	return this.catalogueRepository.findAll();
    }
    
	@Override
	public Collection<Product> findProducts(int locationId) throws DataAccessException {
		return this.catalogueRepository.findByLocation(locationId);
	}

}
