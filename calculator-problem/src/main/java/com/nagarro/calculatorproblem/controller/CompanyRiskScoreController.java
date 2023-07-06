package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.CompanyRiskScore;
import com.nagarro.calculatorproblem.service.impl.CompanyRiskScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompanyRiskScoreController {
    @Autowired
    private CompanyRiskScoreServiceImpl service;

    @GetMapping("/getAllCompanyRiskScore")
    public List<CompanyRiskScore> getAllCompanyRiskScore() {
        return service.getAllCompanyRiskScore();
    }

    @GetMapping("/getCompanyRiskScore/{companyName}")
    public CompanyRiskScore getCompanyRiskScore(@PathVariable String companyName) {
        return service.getCompanyRiskScore(companyName);
    }

    @PostMapping("/addCompanyRiskScore")
    public CompanyRiskScore addCompanyRiskScore(@RequestBody CompanyRiskScore companyRiskScore) {
        return service.addCompanyRiskScore(companyRiskScore);
    }

    @PutMapping("/updateCompanyRiskScore")
    public CompanyRiskScore updateCompanyRiskScore(@RequestBody CompanyRiskScore companyRiskScore) {
        return service.updateCompanyRiskScore(companyRiskScore);
    }

    @DeleteMapping("/deleteCompanyRiskScore/{companyName}")
    public void deleteCompanyRiskScore(@PathVariable String companyName) {
        service.deleteCompanyRiskScore(companyName);
    }
}
