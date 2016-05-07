package com.aas.samples.customerproducts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class SubscriptionProductController extends BaseController {

	private final SubscriptionProductService subscriptionService;


    @Autowired
    public SubscriptionProductController(final SubscriptionProductService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

	/**
     * <p>Saves a customer products selection.</p>
     * 
     * <p>Expected HTTP POST and request '/subscriptions'.</p>
     * 
     * @param basket the basket.
     * @param lang the language.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(method=RequestMethod.POST)
    public String subscriptions(@RequestBody final Basket basket, 
    		@RequestParam(value="lang", required=false) String language, 
    		final Model model) {
    	final Subscription subscription = new Subscription(basket);

    	setLanguage(language, model);
    	this.subscriptionService.save(subscription);
    	
    	return "success";
    }

	/**
     * <p>Retrieves a list of all the products the specified customer is subscribed to.</p>
     * 
     * <p>Expected HTTP GET and request '/subscriptions/{customerId}'.</p>
     * 
     * @param customerId the customer's ID.
     * @return the subscriptions.
     */
    @RequestMapping(value="/data/{customerId}", method=RequestMethod.GET)
    public @ResponseBody Basket subscriptionsData(@PathVariable int customerId) {
    	return new Basket(this.subscriptionService.findBySubscriptionId(customerId));
    }

	/**
     * <p>Retrieves a list of all the products the specified customer is subscribed to.</p>
     * 
     * <p>Expected HTTP GET and request '/subscriptions/{customerId}'.</p>
     * 
     * @param customerId the customer's ID.
     * @param lang the language.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/view/{customerId}", method=RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    public String subscriptions(@PathVariable int customerId, 
    		@RequestParam(value="lang", required=false) String language, final Model model) {
    	final Subscription subscriptions = this.subscriptionService.findBySubscriptionId(customerId);

    	setLanguage(language, model);
    	model.addAttribute("subscriptions", subscriptions);
    	
    	return "subscriptions/subscriptions";
    }

	/**
     * <p>Replies with the success page.</p>
     * 
     * <p>Expected HTTP GET and request '/success'.</p>
     * 
     * @param lang the language.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/success", method=RequestMethod.GET)
    public String success(@RequestParam(value="lang", required=false) String language, 
    		final Model model) {
    	setLanguage(language, model);

    	return "success";
    }

}
