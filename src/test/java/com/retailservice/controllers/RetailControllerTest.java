package com.retailservice.controllers;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.retailservice.RetailServiceApplication;
import com.retailservice.entity.RetailRecord;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RetailServiceApplication.class})
@WebAppConfiguration
public class RetailControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCustomerRewards() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        URL url = Resources.getResource("RetailRecord.json");
        String jsonString = Resources.toString(url, Charsets.UTF_8);

        MvcResult result = mockMvc.perform(post("/rewards")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders)
                .content(jsonString))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);
        assertNotNull(result.getResponse().getContentAsString());

        mockMvc.perform(post("/rewards")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders)
                .content(""))
                .andExpect(status().is4xxClientError());


    }

    public String jsonParser(FileReader reader) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        return obj.toString();
    }

}
