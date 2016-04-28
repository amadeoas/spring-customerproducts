package com.aas.samples.customerproducts.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.core.style.ToStringCreator;


/**
 * Simple JavaBean domain object representing a product.
 *
 * @author Amadeo Asco
 */
@Entity
@Table(name = "products")
public class Product extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    public Category getCategory() {
        return this.category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }
    
    /**
     * Checks if this product is valid for the specified location.
     * 
     * @param locationId the ID of the allocation to verify.
     * @return true if this product is valid for the specified location, or 
     * 			false otherwise.
     */
    public boolean valid(final int locationId) {
    	return this.location.getId() == locationId;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("id", getId())
            .append("name", getName())
            .append("category", getCategory().getName())
            .append("location", getLocation().getName())
            .toString();
    }

}
