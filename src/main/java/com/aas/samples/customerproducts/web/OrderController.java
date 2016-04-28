package com.aas.samples.customerproducts.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aas.samples.customerproducts.model.CatalogueCategory;


/**
 * Controller used to process each customer's order.
 * 
 * @author Amadeo Asco
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	/**
     * <p>Saves a customer products selection.</p>
     * 
     * <p>Expected HTTP POST and request '/order'.</p>
     */
    @RequestMapping(method=RequestMethod.POST)
    public String order(@RequestBody final CatalogueCategory order, 
    		final Map<String, Object> model) {
    	// TODO: save the selections
    	System.out.println(order);
    	
    	return "Your selections has been processed!";
    }

}
