package com.aas.samples.customerproducts.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.aas.samples.customerproducts.model.CatalogueCategory;
import com.aas.samples.customerproducts.model.Product;
import com.aas.samples.customerproducts.service.CatalogueService;
import com.aas.samples.customerproducts.service.CustomerLocationService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/**
 * Controller used to access the products.
 * 
 * @author Amadeo Asco
 */
@Controller
@RequestMapping("/catalogue")
public class CatalogueController {

    private final CatalogueService catalogueService;
    private final CustomerLocationService customerLocationService;


    @Autowired
    public CatalogueController(final CatalogueService catalogueService,
    		final CustomerLocationService customerLocationService) {
        this.catalogueService = catalogueService;
        this.customerLocationService = customerLocationService;
    }

    @InitBinder
    public void setAllowedFields(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	/**
     * <p>Retrieves a list of all the products available for the specified customer.</p>
     * 
     * <p>Expected HTTP GET and request '/order'.</p>
     * 
     * @param customerId the customer's ID.
     * @param model the model.
     */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "text/html")
    public String initCustomerProductsForm(@PathVariable int customerId, 
    		final Map<String, Object> model) {
		final int locationId = this.customerLocationService.findLocation(customerId);
    	final Collection<Product> products = this.catalogueService.findProducts(locationId);
    	final Collection<CatalogueCategory> categories = new ArrayList<>();
    	
    	for (final Product product : products) {
    		CatalogueCategory ccategory = null;
    		
    		for (final CatalogueCategory cc: categories) {
    			if (cc.getCategory().equals(product.getCategory().getName())) {
    				ccategory = cc;

    				break;
    			}
    		}
    		if (ccategory == null) {
    			ccategory = new CatalogueCategory(product.getCategory().getName());
    			categories.add(ccategory);
    		}
    		ccategory.add(product);
    	}
    	model.put("categories", categories);

        return "products/selectionList";
    }

	/**
     * <p>Retrieves a list of all the products, the catalog.</p>
     * 
     * <p>Expected HTTP GET and request '/order'.</p>
     * 
     * @param model the model.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initList(final Map<String, Object> model) {
    	final Collection<Product> products = this.catalogueService.getAllProducts();

    	products.stream().sorted((p, q) -> p.getCategory().getName().compareTo(q.getCategory().getName()));
    	model.put("products", products);

        return "products/productList";
    }
    
    /**
     * Prepares the list of products per category.
     * 
     * @param products the products.
     * @return the products grouped per category.
     */
    protected Collection<CatalogueCategory> buildCategories(
    		final Collection<Product> products) {
		final Collection<CatalogueCategory> categories = new ArrayList<>();
		
		for (final Product product : products) {
			CatalogueCategory ccategory = null;
			
			for (final CatalogueCategory cc: categories) {
				if (cc.getCategory().equals(product.getCategory().getName())) {
					ccategory = cc;
	
					break;
				}
			}
			if (ccategory == null) {
				ccategory = new CatalogueCategory(product.getCategory().getName());
				categories.add(ccategory);
			}
			ccategory.add(product);
		}
	
		return categories;
    }

}
