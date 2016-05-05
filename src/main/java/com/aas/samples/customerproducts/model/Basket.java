package com.aas.samples.customerproducts.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Representation of basket of subscriptions.
 * 
 * @author aasco
 */
public class Basket extends CatalogueCategory {

	private Customer customer;
	private boolean changed;

	
	public Basket() {
	}

	public Basket(final Subscription subscription) {
		super("Basket");

		final List<Product> products = new ArrayList<>();
		this.customer = subscription.getCustomer();
		for (final SubscriptionProduct sp : subscription.getProducts()) {
			products.add(sp.getProduct());
		}
		this.setProducts(products);
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}
	
	public boolean hasChanged() {
		return this.changed;
	}
	
	public void setChanged(final boolean changed) {
		this.changed = changed;
	}

}
