package com.andela.plans.service.impl;

import com.andela.plans.model.Member;
import com.andela.plans.model.Plan;
import com.andela.plans.repository.MemberRepository;
import com.andela.plans.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> findByPlan(Integer planId) {
        Plan plan = new Plan();
        plan.setId(planId);
        return memberRepository.findByPlan(plan);
    }

    @Override
    public void assignPlan(Integer memberId, Integer planId) {
        Member member = findOne(memberId);
        Plan plan = new Plan();
        plan.setId(planId);
        member.setPlan(plan);
        super.save(member);
    }
}
