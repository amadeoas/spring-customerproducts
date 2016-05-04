package com.aas.samples.customerproducts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.aas.samples.customerproducts.model.Customer;
import com.aas.samples.customerproducts.model.Location;
import com.aas.samples.customerproducts.model.Subscription;
import com.aas.samples.customerproducts.model.SubscriptionProduct;


/**
 * <p>Base class for {@link SubscriptionProductService} integration tests. </p> <p> Subclasses should specify Spring context
 * configuration using {@link ContextConfiguration @ContextConfiguration} annotation </p> <p>
 * AbstractclinicServiceTests and its subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances, meaning that
 * we don't need to perform application context lookups. See the use of {@link Autowired @Autowired} on the <code>{@link
 * AbstractSubscriptionProductServiceTests#clinicService clinicService}</code> instance variable, which uses autowiring <em>by
 * type</em>. <li><strong>Transaction management</strong>, meaning each test method is executed in its own transaction,
 * which is automatically rolled back by default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script. <li> An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean lookup if necessary. </li> </ul>
 *
 * @author Amadeo Asco
 */
public abstract class AbstractSubscriptionProductServiceTests {

    @Autowired
    protected SubscriptionProductService subscriptionProductService;


    @Test
    public void shouldSave() {
    	final Location location = new Location();
    	final Customer customer = new Customer();
    	final List<SubscriptionProduct> products = new ArrayList<>();
    	final Subscription subscription;
    	
    	location.setId(2);
    	location.setName("LONDON");
    	customer.setId(2);
    	customer.setFirstName("James");
    	customer.setLastName("Carter");
    	customer.setLocation(location);
    	subscription = new Subscription(customer, products);

    	this.subscriptionProductService.save(subscription);
    }

    @Test
    public void shouldGetSubscription() {
    	final int customerId = 2;
        final Subscription subscription = this.subscriptionProductService.findBySubscriptionId(customerId);

        assertThat(subscription).isNotNull();
        assertThat(subscription.getCustomer()).isNotNull();
        assertThat(subscription.getCustomer().getId()).isEqualTo(customerId);
        assertThat(subscription.getCustomer().getLocation()).isNotNull();
    }

}
