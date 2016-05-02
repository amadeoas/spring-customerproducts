package com.aas.samples.customerproducts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.aas.samples.customerproducts.model.Product;


/**
 * <p>Base class for {@link CatalogueService} integration tests. </p> <p> Subclasses should specify Spring context
 * configuration using {@link ContextConfiguration @ContextConfiguration} annotation </p> <p>
 * AbstractclinicServiceTests and its subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances, meaning that
 * we don't need to perform application context lookups. See the use of {@link Autowired @Autowired} on the <code>{@link
 * AbstractCusAbstratCatalogueServiceTeststomerServiceTests#clinicService clinicService}</code> instance variable, which uses autowiring <em>by
 * type</em>. <li><strong>Transaction management</strong>, meaning each test method is executed in its own transaction,
 * which is automatically rolled back by default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script. <li> An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean lookup if necessary. </li> </ul>
 *
 * @author Amadeo Asco
 */
public abstract class AbstratCatalogueServiceTests {

    @Autowired
    protected CatalogueService catalogueService;


    @Test
    public void shouldFindAllProducts() {
        final Collection<Product> products = this.catalogueService.getAllProducts();

        assertThat(products.size()).isEqualTo(5);
    }

    @Test
    public void shouldFindProduct() {
    	final int locationId = 2;
        final Collection<Product> products = this.catalogueService.findProducts(locationId);
        int iNumProductsWithoutLoc = 2;

        assertThat(products.size()).isEqualTo(4);
        for (final Product product : products) {
        	final String location = product.getLocation().getName();

        	// Ignore products without location
        	if (location.length() > 0) {
        		assertThat(location).isEqualTo("LONDON");
        	} else {
        		--iNumProductsWithoutLoc;
        	}
        }
        assertThat(iNumProductsWithoutLoc).isEqualTo(0);
    }

}
