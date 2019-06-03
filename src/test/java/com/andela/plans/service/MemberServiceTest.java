package com.andela.plans.service;

import com.andela.plans.model.Member;
import com.andela.plans.model.Plan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PlanService planService;

    private Member member;

    private Plan goldPlan;

    private Plan silverPlan;

    @Before
    public void setUp() {
        member = new Member();
        member.setFirstName("test");
        member.setLastName("test2");
        member.setBirthDate(LocalDate.now());
        goldPlan = new Plan();
        goldPlan.setName("Gold");
        planService.save(goldPlan);
        silverPlan = new Plan();
        silverPlan.setName("Silver");
        planService.save(silverPlan);
        member.setPlan(silverPlan);
        memberService.save(member);
    }

    @After
    public void tearDown() {
        memberService.delete(member.getId());
        planService.delete(goldPlan.getId());
        planService.delete(silverPlan.getId());
    }

    @Test
    public void findMemberByPlan() {
        assertTrue(memberService.findByPlan(silverPlan.getId()).size() == 1);
    }

    @Test
    public void assignPlan() {
        assertEquals(memberService.findOne(member.getId()).getPlan().getId(), silverPlan.getId());
        memberService.assignPlan(member.getId(), goldPlan.getId());
        assertEquals(memberService.findOne(member.getId()).getPlan().getId(), goldPlan.getId());
    }
}
