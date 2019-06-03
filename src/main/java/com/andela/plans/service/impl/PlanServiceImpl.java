package com.andela.plans.service.impl;

import com.andela.plans.exceptions.PlanLimitedException;
import com.andela.plans.model.Plan;
import com.andela.plans.service.PlanService;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl extends BaseServiceImpl<Plan> implements PlanService {
    @Override
    public void save(Plan entity) {
        if(!entity.isValid())
            throw new PlanLimitedException();
        super.save(entity);
    }
}
