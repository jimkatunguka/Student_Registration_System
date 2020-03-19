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
public class StudentControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    // Sample Student Token which last for sometime
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
    public void addEnrollment() throws Exception {
        MvcResult result = mockMvc.perform(post("/students/980/enrollment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
                .header("Authorization","Bearer "+studentToken))
                .andDo(print())
                .andReturn();
        assertTrue(result.getResponse().getStatus() == 403 || result.getResponse().getStatus() == 201);
    }

    @Test
    public void getStudentEnrollment() throws Exception {
        MvcResult result = mockMvc.perform(get("/students/980/enrollment")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization","Bearer "+studentToken))
                                    .andDo(print())
                                    .andReturn();
        assertTrue(result.getResponse().getStatus() == 200
                || result.getResponse().getStatus() == 404);
    }

    @Test
    public void getStudentEnrollmentById() throws Exception {
        // Student Add a enrollment use case
        mockMvc.perform(post("/students/980/enrollment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
                .header("Authorization","Bearer "+studentToken));

        //
        mockMvc.perform(get("/students/980/enrollment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+studentToken))
                .andDo(print())
                .andExpect(status().isOk());
    }
}