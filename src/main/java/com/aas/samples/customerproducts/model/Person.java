package com.aas.samples.customerproducts.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Amadeo Asco
 */
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    protected String firstName;

    @Column(name = "last_name")
    @NotEmpty
    protected String lastName;


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

}
