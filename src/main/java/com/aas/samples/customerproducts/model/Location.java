package com.aas.samples.customerproducts.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Model of a {@link Location} (for example, LONDON).
 *
 * @author Amadeo Asco
 */
@Entity
@Table(name = "locations")
public class Location extends NamedEntity {

}
