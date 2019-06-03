package com.andela.plans.controller;

import com.andela.plans.model.Plan;
import com.andela.plans.service.PlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanController.class)
public class PlanControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlanService planService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {
        Plan plan = new Plan();
        plan.setName("ahmed");

        given(planService.findAll()).willReturn(Arrays.asList(plan));

        mvc.perform(get("/api/plan/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(plan.getName())));
    }


    @Test
    public void findById() throws Exception {
        Plan plan = new Plan();
        plan.setName("ahmed");

        given(planService.findOne(1)).willReturn(plan);

        mvc.perform(get("/api/plan/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(plan.getName())));
    }

    @Test
    public void save() throws Exception {
        Plan plan = new Plan();
        plan.setName("ahmed");
        String inputJson = objectMapper.writeValueAsString(plan);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/plan/")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void update() throws Exception {
        Plan plan = new Plan();
        plan.setName("ahmed");
        String inputJson = objectMapper.writeValueAsString(plan);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/plan/1/")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteRequest() throws Exception {
        mvc.perform(delete("/api/plan/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
