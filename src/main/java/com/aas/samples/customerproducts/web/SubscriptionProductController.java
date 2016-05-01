package com.aas.samples.customerproducts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aas.samples.customerproducts.model.Basket;
import com.aas.samples.customerproducts.model.Subscription;
import com.aas.samples.customerproducts.service.SubscriptionProductService;


/**
 * Controller used to process and access each customer's subscriptions.
 * 
 * @author Amadeo Asco
 */
@Controller
@RequestMapping("/subscriptions")
public class SubscriptionProductController {

	private final SubscriptionProductService subscriptionService;


    @Autowired
    public SubscriptionProductController(final SubscriptionProductService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

	/**
     * <p>Saves a customer products selection.</p>
     * 
     * <p>Expected HTTP POST and request '/subscriptions'.</p>
     * @param model the model.
     */
    @RequestMapping(method=RequestMethod.POST)
    public String order(@RequestBody final Basket basket, final Model model) {
    	final Subscription subscription = new Subscription(basket);

    	this.subscriptionService.save(subscription);
    	
    	return "success";
    }

	/**
     * <p>Retrieves a list of all the products the specified customer is subscribed to.</p>
     * 
     * <p>Expected HTTP GET and request '/subscriptions/{customerId}'.</p>
     * 
     * @param customerId the customer's ID.
     * @param model the model.
     */
    @RequestMapping(value="/{customerId}", method=RequestMethod.GET, produces = "text/html")
    public String order(@PathVariable int customerId, final Model model) {
    	final Subscription subscriptions = this.subscriptionService.findByCustomerId(customerId);

    	model.addAttribute("subscriptions", subscriptions);
    	
    	return "subscriptions/subscriptions";
    }

	/**
     * <p>Reply with the success page.</p>
     * 
     * <p>Expected HTTP GET and request '/success'.</p>
     * @param model the model.
     */
    @RequestMapping(value="/success", method=RequestMethod.GET)
    public String success(final Model model) {
        return "success";
    }

}
