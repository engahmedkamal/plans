package com.andela.plans.service;

import com.andela.plans.exceptions.PlanLimitedException;
import com.andela.plans.model.Plan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanServiceTest {
    @Autowired
    private PlanService planService;

    @Test(expected = PlanLimitedException.class)
    public void testSaveInvalidPlan(){
        Plan plan = new Plan();
        plan.setName("Gold");
        plan.setLimited(true);
        planService.save(plan);
    }
}
