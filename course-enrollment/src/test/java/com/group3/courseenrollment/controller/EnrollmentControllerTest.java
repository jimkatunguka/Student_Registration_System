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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrollmentControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    // Sample Admin Token which last for sometime
    private String adminToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaGlsIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfRkFDVUxUWSIsIlJPTEVfU1RVREVOVCJdLCJpc3MiOiJ3aW5kLWdyb3VwIiwiZXhwIjoxNTg1NDEwNTA1LCJpYXQiOjE1ODQ1NDY1MDV9.88zgxDMw1EObD8j0x8qWSmqg-TLcA8khq3o5lEZr_7gDoUYsKd5Qrf0mhUYBwkSQd5pgJFaf3PrhxFL5-XtwrQ";


    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    public void updateEnrollment() throws Exception {
        String enrollmentJson = "{\n" +
                "\t\"enrolStartDate\":\"2020-03-17\",\n" +
                "\t\"enrolEndDate\":\"2020-03-30\"\n" +
                "}";

        MvcResult result = mockMvc.perform(put("/1/980")
                                    .header("Authorization","Bearer "+adminToken)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(enrollmentJson))
                                    .andDo(print())
                                    .andReturn();
        assertTrue(result.getResponse().getStatus() == 200 || result.getResponse().getStatus() == 404);
    }
}