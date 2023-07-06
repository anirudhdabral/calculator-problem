package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.RiskCalcLogic;
import com.nagarro.calculatorproblem.service.impl.RiskCalcLogicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RiskCalcLogicController {
    @Autowired
    private RiskCalcLogicServiceImpl service;

    @GetMapping("/getAllRiskCalcLogic")
    public List<RiskCalcLogic> getAllRiskCalcLogic() {
        return service.getAllRiskCalcLogic();
    }

    @GetMapping("/getRiskCalcLogic/{elementName}")
    public RiskCalcLogic getRiskCalcLogic(@PathVariable String elementName) {
        return service.getRiskCalcLogic(elementName);
    }

    @PostMapping("/addRiskCalcLogic")
    public RiskCalcLogic addRiskCalcLogic(@RequestBody RiskCalcLogic riskCalcLogic) {
        return service.addRiskCalcLogic(riskCalcLogic);
    }

    @PutMapping("/updateRiskCalcLogic")
    public RiskCalcLogic updateRiskCalcLogic(@RequestBody RiskCalcLogic riskCalcLogic) {
        return service.updateRiskCalcLogic(riskCalcLogic);
    }

    @DeleteMapping("/deleteRiskCalcLogic/{elementName}")
    public void deleteRiskCalcLogic(@PathVariable String elementName) {
        service.deleteRiskCalcLogic(elementName);
    }
}
