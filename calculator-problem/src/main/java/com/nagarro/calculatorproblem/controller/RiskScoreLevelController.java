package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.RiskScoreLevel;
import com.nagarro.calculatorproblem.service.impl.RiskScoreLevelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RiskScoreLevelController {
    @Autowired
    private RiskScoreLevelServiceImpl service;

    @GetMapping("/getAllRiskScoreLevel")
    public List<RiskScoreLevel> getAllRiskScoreLevel() {
        return service.getAllRiskScoreLevel();
    }

    @GetMapping("/getRiskScoreLevel/{score}")
    public RiskScoreLevel getRiskScoreLevel(@PathVariable String score) {
        return service.getRiskScoreLevel(score);
    }

    @PostMapping("/addRiskScoreLevel")
    public RiskScoreLevel addRiskScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {
        return service.addRiskScoreLevel(riskScoreLevel);
    }

    @PutMapping("/updateRiskScoreLevel")
    public RiskScoreLevel updateRiskScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {
        return service.updateRiskScoreLevel(riskScoreLevel);
    }

    @DeleteMapping("/deleteRiskScoreLevel/{score}")
    public void deleteRiskScoreLevel(@PathVariable String score) {
        service.deleteRiskScoreLevel(score);
    }
}
