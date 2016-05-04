package com.aas.samples.customerproducts.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aas.samples.customerproducts.web.CatalogueController;


/**
 * Test class for the {@link CatalogueController}.
 *
 * @author Amadeo Asco
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/business-config.xml", "classpath:spring/tools-config.xml", "classpath:spring/mvc-core-config.xml"})
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class CatalogueControllerTests {

    @Autowired
    private CatalogueController catalogueController;

    @Autowired
    private FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(this.catalogueController)
            .setConversionService(this.formattingConversionServiceFactoryBean.getObject())
            .build();
    }

    @Test
    public void testInitCatalogueProductsForm() throws Exception {
        this.mockMvc.perform(get("/catalogue/{customerId}", 1))
            .andExpect(status().isOk())
            .andExpect(view().name("products/subscriptionsList"))
            .andExpect(forwardedUrl("products/subscriptionsList"));
    }

    @Test
    public void testInitList() throws Exception {
        this.mockMvc.perform(get("/catalogue"))
            .andExpect(status().isOk())
            .andExpect(view().name("products/productList"))
            .andExpect(forwardedUrl("products/productList"));
    }

}
