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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * {@link CourseControllerTest} Integration testing on Course
 */
@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseControllerTest {

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
    public void getAllCourses() throws Exception {
        mockMvc.perform(get("/courses")
                .header("Authorization","Bearer "+adminToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addCourse() throws Exception {
        String courses = "{\n" +
                "\t\"code\":\"CS544\",\n" +
                "\t\"name\":\"Enterprise Architecture\",\n" +
                "\t\"description\":\"Spring and Hibernate\"\n" +
                "}";

        mockMvc.perform(post("/courses")
                .header("Authorization","Bearer "+adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(courses))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getCourse() throws Exception {
        mockMvc.perform(get("/courses/CS544")
                .header("Authorization","Bearer "+adminToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        String courses = "{\n" +
                "\t\"code\":\"CS544\",\n" +
                "\t\"name\":\"Enterprise Architecture\",\n" +
                "\t\"description\":\"Spring and Hibernate(Update)\"\n" +
                "}";

        MvcResult result = mockMvc.perform(put("/courses/CS544")
                .header("Authorization","Bearer "+adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(courses))
                .andDo(print())
                .andReturn();

        assertTrue(result.getResponse().getStatus() == 200
                || result.getResponse().getStatus() == 404);
    }
}