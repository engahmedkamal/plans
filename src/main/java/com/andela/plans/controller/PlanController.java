package com.andela.plans.controller;

import com.andela.plans.model.Plan;
import com.andela.plans.service.PlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("/")
    @ApiOperation(value = "find  All")
    public ResponseEntity<List<Plan>> findAll() {
        return new ResponseEntity<>(planService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    @ApiOperation(value = "find  By ID")
    public ResponseEntity<Plan> findOne(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(planService.findOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new ")
    public ResponseEntity<String> save(@RequestBody Plan plan) {
        planService.save(plan);
        return new ResponseEntity<>("Created with Id:" + plan.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/")
    @ApiOperation(value = "Update")
    public ResponseEntity<String> update(@PathVariable(name = "id") Integer id, @RequestBody Plan plan) {
        plan.setId(id);
        planService.save(plan);
        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    @ApiOperation(value = "delete")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        planService.delete(id);
        return new ResponseEntity<>("delete Success", HttpStatus.OK);
    }
}
