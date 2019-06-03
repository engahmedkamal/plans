package com.andela.plans.controller;

import com.andela.plans.model.Member;
import com.andela.plans.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    @ApiOperation(value = "find All")
    public ResponseEntity<List<Member>> findAll() {
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/plan/{id}/")
    @ApiOperation(value = "find All By Plan")
    public ResponseEntity<List<Member>> findByPlan(@PathVariable(name = "id") Integer planId) {
        return new ResponseEntity<>(memberService.findByPlan(planId), HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    @ApiOperation(value = "find By ID")
    public ResponseEntity<Member> findOne(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(memberService.findOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new ")
    public ResponseEntity<String> save(@RequestBody Member member) {
        memberService.save(member);
        return new ResponseEntity<>("Created with Id:" + member.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/")
    @ApiOperation(value = "Update")
    public ResponseEntity<String> update(@PathVariable(name = "id") Integer id, @RequestBody Member member) {
        member.setId(id);
        memberService.save(member);
        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    @ApiOperation(value = "delete")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        memberService.delete(id);
        return new ResponseEntity<>("delete Success", HttpStatus.OK);
    }

    @PutMapping("/{id}/plan/{planId}/")
    @ApiOperation("assign Plan")
    public ResponseEntity<Void> assignPlan(@PathVariable(name="id") Integer memberID, @PathVariable(name = "planId") Integer planId) {
        memberService.assignPlan(memberID, planId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
