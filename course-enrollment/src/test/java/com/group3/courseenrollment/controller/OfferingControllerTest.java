package com.group3.courseenrollment.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferingControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    // Sample Admin Token which last for sometime
    private String adminToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaGlsIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfRkFDVUxUWSIsIlJPTEVfU1RVREVOVCJdLCJpc3MiOiJ3aW5kLWdyb3VwIiwiZXhwIjoxNTg1NDEwNTA1LCJpYXQiOjE1ODQ1NDY1MDV9.88zgxDMw1EObD8j0x8qWSmqg-TLcA8khq3o5lEZr_7gDoUYsKd5Qrf0mhUYBwkSQd5pgJFaf3PrhxFL5-XtwrQ";


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    public void getAllOfferings() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/offerings")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization","Bearer "+adminToken))
                                    .andDo(print())
                                    .andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200 || mvcResult.getResponse().getStatus() == 404);
    }

    @Test
    public void getOffering() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/offerings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+adminToken))
                .andDo(print())
                .andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200 || mvcResult.getResponse().getStatus() == 404);
    }

    @Test
    public void addOffering() throws Exception {
        String offeringJson = "{\n" +
                "    \"courseId\": \"CS544\",\n" +
                "    \"blockId\":\"203\",\n" +
                "    \"sections\":[1]\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(post("/offerings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(offeringJson)
                .header("Authorization","Bearer "+adminToken))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(mvcResult.getResponse().getStatus() == 201 || mvcResult.getResponse().getStatus() == 404);
    }
}