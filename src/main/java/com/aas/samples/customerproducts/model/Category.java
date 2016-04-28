package com.aas.samples.customerproducts.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Simple JavaBean domain object representing a category.
 * 
 * @author Amadeo Asco
 */
@Entity
@Table(name = "categories")
public class Category extends NamedEntity {

}
