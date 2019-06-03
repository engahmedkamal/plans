package com.andela.plans.repository;

import com.andela.plans.model.Member;
import com.andela.plans.model.Plan;

import java.util.List;

public interface MemberRepository extends BaseRepository<Member> {
    List<Member> findByPlan(Plan plan);
}
