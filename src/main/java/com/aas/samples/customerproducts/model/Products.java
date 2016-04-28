package com.aas.samples.customerproducts.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Simple domain object representing a list of products. Mostly here to be used 
 * for the 'products' {@link org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * @author Amadeo Asco
 */
@XmlRootElement
public class Products {

    private List<Product> products;

    @XmlElement
    public List<Product> getProductsList() {
        if (this.products == null) {
        	this.products = new ArrayList<>();
        }

        return this.products;
    }

}
