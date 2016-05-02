package com.aas.samples.customerproducts.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Integration test using the 'Spring Data' profile.
 *
 * @author Amadeo Asco
 * @see AbstratCatalogueServiceTests for more details.
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa")
public class CatalogueServiceSpringDataJpaTests extends AbstratCatalogueServiceTests {

}
