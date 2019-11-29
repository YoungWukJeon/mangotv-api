package com.study.mangotv.common.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


// TODO: 예외 상황 Test
@ExtendWith(value = SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private HealthCheckController healthCheckController;


    @Test
    void healthCheck_정상() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/health").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult);
    }
}