package com.aas.samples.customerproducts.web;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.service.CustomerService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller used to access the customer's details.
 * 
 * @author Amadeo Asco
 */
@Controller
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerService customerService;


    @Autowired
    public CustomersController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @InitBinder
    public void setAllowedFields(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	/**
     * <p>Retrieves a list of all the customers.</p>
     * 
     * <p>Expected HTTP GET and request '/customer'.</p>
     * 
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initList(final Model model) {
    	final Collection<Customer> customers = this.customerService.getAllCustomers();

    	model.addAttribute("language","en");
    	model.addAttribute("customers", customers);

        return "customers/customersList";
    }

}
