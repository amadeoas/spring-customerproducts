package com.aas.samples.customerproducts.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Simple JavaBean domain object with an ID property. Used as a base class for 
 * objects needing this property.
 *
 * @author Amadeo Asco
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

}
