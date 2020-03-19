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
public class SectionControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    // Sample Admin Token which last for sometime
    private String adminToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaGlsIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfRkFDVUxUWSIsIlJPTEVfU1RVREVOVCJdLCJpc3MiOiJ3aW5kLWdyb3VwIiwiZXhwIjoxNTg1NDEwNTA1LCJpYXQiOjE1ODQ1NDY1MDV9.88zgxDMw1EObD8j0x8qWSmqg-TLcA8khq3o5lEZr_7gDoUYsKd5Qrf0mhUYBwkSQd5pgJFaf3PrhxFL5-XtwrQ";
    // Sample Student Token
    private String studentToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaW0iLCJyb2xlcyI6WyJST0xFX1NUVURFTlQiXSwiaXNzIjoid2luZC1ncm91cCIsImV4cCI6MTU4NTQxODA5NSwiaWF0IjoxNTg0NTU0MDk1fQ.jCa8gtM5JiQeTFgcQLqYfv-IFLY2PGuN7pOhbqr-Fns350GaJaj8dIY3ogqiPx7G7DaOvwvbxV7N3ovEDPwN8g";


    String requestContent = "[\n" +
            "    {\n" +
            "        \"enrolStartDate\": \"2020-03-18\",\n" +
            "        \"enrolEndDate\": \"2020-03-30\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"enrolStartDate\": \"2020-04-11\",\n" +
            "        \"enrolEndDate\": \"2020-04-30\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"enrolStartDate\": \"2020-05-03\",\n" +
            "        \"enrolEndDate\": \"2020-05-30\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"enrolStartDate\": \"2020-05-03\",\n" +
            "        \"enrolEndDate\": \"2020-05-30\"\n" +
            "    }\n" +
            "]";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    public void getSections() throws Exception {
        mockMvc.perform(get("/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+adminToken))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void addSections() throws Exception {
        // Student Selecting enrollment use case
        mockMvc.perform(post("/students/980/enrollment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
                .header("Authorization","Bearer "+studentToken));

        String requestContent = "{\n" +
                "\t\"enrollmentCodes\":[1,2],\n" +
                "\t\"facultyId\": 1\n" +
                "}";

        mockMvc.perform(post("/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
                .header("Authorization","Bearer "+adminToken))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}