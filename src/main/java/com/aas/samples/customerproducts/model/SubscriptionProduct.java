package com.aas.samples.customerproducts.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Simple JavaBean domain object representing a product part of a subscription.
 *
 * @author Amadeo Asco
 */
@Entity
@Table(name = "subscription_products")
public class SubscriptionProduct extends BaseEntity {

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", 
    		insertable = false, updatable = false)
    private Timestamp created;
    
    
    public SubscriptionProduct() {
    }

    public SubscriptionProduct(final Customer customer, 
    		final Product product) {
    	this.customer = customer;
    	this.product = product;
    }
    
    public Customer getCustomer() {
    	return this.customer;
    }
    
    public void setCustomer(final Customer customer) {
    	this.customer = customer;
    }

    public Product getProduct() {
    	return this.product;
    }
    
    public void setOrder(final Product product) {
    	this.product = product;
    }

    public Timestamp getCreated() {
    	return this.created;
    }

    public void setCreated(final Timestamp created) {
    	this.created = created;
    }
    
}
