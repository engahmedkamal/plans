package com.andela.plans.service;

import com.andela.plans.model.Member;

import java.util.List;

public interface MemberService extends BaseService<Member>{
    List<Member> findByPlan(Integer planId);
    void assignPlan(Integer memberId, Integer planId);
}
