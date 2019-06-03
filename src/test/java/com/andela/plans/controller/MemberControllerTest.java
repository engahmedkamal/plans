package com.andela.plans.controller;

import com.andela.plans.model.Member;
import com.andela.plans.service.MemberService;
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
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {
        Member member = new Member();
        member.setFirstName("ahmed");

        given(memberService.findAll()).willReturn(Arrays.asList(member));

        mvc.perform(get("/api/member/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(member.getFirstName())));
    }

    @Test
    public void findByPlanId() throws Exception {
        Member member = new Member();
        member.setFirstName("ahmed");

        given(memberService.findByPlan(1)).willReturn(Arrays.asList(member));

        mvc.perform(get("/api/member/plan/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(member.getFirstName())));
    }

    @Test
    public void findById() throws Exception {
        Member member = new Member();
        member.setFirstName("ahmed");

        given(memberService.findOne(1)).willReturn(member);

        mvc.perform(get("/api/member/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(member.getFirstName())));
    }

    @Test
    public void save() throws Exception {
        Member member = new Member();
        member.setFirstName("ahmed");
        String inputJson = objectMapper.writeValueAsString(member);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/member/")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void update() throws Exception {
        Member member = new Member();
        member.setFirstName("ahmed");
        String inputJson = objectMapper.writeValueAsString(member);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/member/1/")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteRequest() throws Exception {
        mvc.perform(delete("/api/member/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void assignPlan() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/member/1/plan/1/")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);    }
}
