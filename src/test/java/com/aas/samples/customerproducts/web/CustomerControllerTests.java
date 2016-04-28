package com.aas.samples.customerproducts.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aas.samples.customerproducts.web.CustomersController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Test class for {@link CustomersController}.
 *
 * @author Amadeo Asco
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/business-config.xml", "classpath:spring/tools-config.xml", "classpath:spring/mvc-core-config.xml"})
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class CustomerControllerTests {

    @Autowired
    private CustomersController customersController;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customersController).build();
    }

    @Test
    public void testProcessFindFormSuccess() throws Exception {
        this.mockMvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(view().name("customers/customersList"));
    }

}
