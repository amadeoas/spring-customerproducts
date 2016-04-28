package com.aas.samples.customerproducts.model;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Container of all products in a category.
 * 
 * @author aasco
 */
public class CatalogueCategory {

	private String category;
	private Collection<Product> products;
	
	
	public CatalogueCategory() {
	}

	public CatalogueCategory(final String category) {
		this.category = category;
		this.products = new ArrayList<>();
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(final String category) {
		this.category = category;
	}
	
	public Collection<Product> getProducts() {
		return this.products;
	}
	
	public void setProducts(final Collection<Product> products) {
		this.products = products;
	}
	
	public void add(final Product product) {
		if (!this.category.equals(product.getCategory().getName())) {
			
		}
		
		this.products.add(product);
	}

	@Override
	public String toString() {
		final StringBuilder buf = new StringBuilder();
		boolean bFirst = true;

		buf.append("category=");
		buf.append(this.category);
		buf.append(", products:[");
		for (final Product product : this.products) {
			if (bFirst) {
				bFirst = false;
			} else {
				buf.append(", ");
			}
			buf.append(product.getName());
		}
		buf.append("]");

		return buf.toString();
	}

}