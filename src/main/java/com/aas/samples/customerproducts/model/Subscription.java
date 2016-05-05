package com.aas.samples.customerproducts.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Simple JavaBean domain object representing all the subscriptions for a 
 * customer.
 *
 * @author Amadeo Asco
 */
public class Subscription {

    protected Customer customer;
    protected List<SubscriptionProduct> products;
    
    
    public Subscription(final Customer customer, 
    		final List<SubscriptionProduct> products) {
    	this.customer = customer;
    	this.products = products;
    }
    
    public Subscription(final Basket selections) {
    	// Build
    	this.products = new ArrayList<>();
    	this.customer = selections.getCustomer();
    	for (final Product product : selections.getProducts()) {
    		this.products.add(new SubscriptionProduct(this.customer, product));
    	}
    }
    
    public Customer getCustomer() {
    	return this.customer;
    }

    public void setCustomer(final Customer cutomer) {
    	this.customer = cutomer;
    }
    
    public List<SubscriptionProduct> getProducts() {
    	return this.products;
    }

    public void setProducts(final List<SubscriptionProduct> products) {
    	this.products = products;
    }
    
}
