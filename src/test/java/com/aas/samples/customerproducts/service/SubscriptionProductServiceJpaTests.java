package com.aas.samples.customerproducts.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Integration test using the jpa profile.
 *
 * @author Amadeo Asco
 * @see AbstractSubscriptionProductServiceTests for more details.
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
public class SubscriptionProductServiceJpaTests extends AbstractSubscriptionProductServiceTests {

}
