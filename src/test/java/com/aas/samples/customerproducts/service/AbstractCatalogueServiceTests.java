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
public abstract class AbstractCatalogueServiceTests {

    @Autowired
    protected CatalogueService catalogueService;


    @Test
    public void shouldFindAllProducts() {
        final Collection<Product> products = this.catalogueService.getAllProducts();

        assertThat(products.size()).isEqualTo(5);
        for (final Product product : products) {
        	assertThat(product).isNotNull();
        	assertThat(product.getCategory()).isNotNull();
        	assertThat(product.getCategory().getName()).isNotNull();
        	assertThat(product.getLocation()).isNotNull();
        	assertThat(product.getLocation().getName()).isNotNull();
        }
    }

    @Test
    public void shouldFindProduct() {
    	final int locationId = 2;
        final Collection<Product> products = this.catalogueService.findProducts(locationId);
        int iNumProductsWithoutLoc = 2;

        assertThat(products.size()).isEqualTo(4);
        for (final Product product : products) {
        	assertThat(product).isNotNull();
        	assertThat(product.getCategory()).isNotNull();
        	assertThat(product.getCategory().getName()).isNotNull();
        	assertThat(product.getLocation()).isNotNull();
        	assertThat(product.getLocation().getName()).isNotNull();

        	// Ignore products without location
        	if (product.getLocation().getId() == 1) {
        		--iNumProductsWithoutLoc;
        	} else {
        		assertThat(product.getLocation().getName()).isEqualTo("LONDON");
        	}
        }
        assertThat(iNumProductsWithoutLoc).isEqualTo(0);
    }

}
