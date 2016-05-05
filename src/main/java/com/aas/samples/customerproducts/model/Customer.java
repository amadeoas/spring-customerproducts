package com.aas.samples.customerproducts.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Simple JavaBean domain object representing a customer.
 *
 * @author Amadeo Asco
 */
@Entity
@Table(name = "customers")
public class Customer extends Person {

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    protected Location getLocationInternal() {
        return this.location;
    }

    protected void setLocationInternal(final Location location) {
        this.location = location;
    }

    /**
     * Getter for property location.
     *
     * @return the location.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Setter for property location.
     *
     * @param location new value for the location.
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

}
