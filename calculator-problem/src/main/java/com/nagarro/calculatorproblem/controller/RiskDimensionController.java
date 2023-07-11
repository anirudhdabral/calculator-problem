package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.RiskDimension;
import com.nagarro.calculatorproblem.service.impl.RiskDimensionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RiskDimensionController {
    @Autowired
    private RiskDimensionServiceImpl service;

    @GetMapping("/getAllRiskDimension")
    public List<RiskDimension> getRiskDimensions() {
        return service.getAllRiskDimension();
    }

    @PutMapping("/rebalanceWeight")
    public List<RiskDimension> rebalanceWeight(@RequestBody List<RiskDimension> riskDimensionList) {
        return service.rebalanceWeight(riskDimensionList);
    }
}
