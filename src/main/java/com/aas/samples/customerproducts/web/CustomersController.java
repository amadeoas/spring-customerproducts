package com.aas.samples.customerproducts.web;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.service.CustomerService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller used to access the customer's details.
 * 
 * @author Amadeo Asco
 */
@Controller
@RequestMapping("/customers")
public class CustomersController extends BaseController {

    private final CustomerService customerService;


    @Autowired
    public CustomersController(final CustomerService customerService) {
        this.customerService = customerService;
    }

	/**
     * <p>Retrieves a list of all the customers.</p>
     * 
     * <p>Expected HTTP GET and request '/customer'.</p>
     * 
     * @param lang the language.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initList(@RequestParam(value="lang", required=false) String language, final Model model) {
    	final Collection<Customer> customers = this.customerService.getAllCustomers();

    	setLanguage(language, model);
    	model.addAttribute("customers", customers);

        return "customers/customersList";
    }

}
