package com.aas.samples.customerproducts.model;


/**
 * Representation of basket of subscriptions.
 * 
 * @author aasco
 */
public class Basket extends CatalogueCategory {

	private Customer customer;

	
	public Basket() {
	}

	public Basket(final Subscription subscription) {
		super("Basket");

		this.customer = subscription.getCustomer();
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

}
