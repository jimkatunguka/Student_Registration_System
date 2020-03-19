package com.group3.courseenrollment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.courseenrollment.domain.Block;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration Tests on Block Controller test
 */

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // Sample Admin Token which last for sometime
    private String adminToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaGlsIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfRkFDVUxUWSIsIlJPTEVfU1RVREVOVCJdLCJpc3MiOiJ3aW5kLWdyb3VwIiwiZXhwIjoxNTg1NDEwNTA1LCJpYXQiOjE1ODQ1NDY1MDV9.88zgxDMw1EObD8j0x8qWSmqg-TLcA8khq3o5lEZr_7gDoUYsKd5Qrf0mhUYBwkSQd5pgJFaf3PrhxFL5-XtwrQ";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void addBlock() throws Exception {
        String blockJson = "{\n" +
                "\t\"code\":\"203\",\n" +
                "\t\"name\":\"Block A\",\n" +
                "\t\"semester\":\"Fall\",\n" +
                "\t\"blockSeqNum\":\"3949\",\n" +
                "\t\"startDate\":\"2020-03-18\",\n" +
                "\t\"endDate\":\"2020-04-18\"\n" +
                "}";
        //
        mockMvc.perform(post("/blocks")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+adminToken)
                .content(blockJson))
                .andDo(print())
                .andExpect(status().isCreated());


    }

    @Test
    public void loadBlocks() throws Exception {
        mockMvc.perform(get("/blocks/203")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+adminToken))
                .andDo(print())
                .andExpect(status().isOk());
    }


}