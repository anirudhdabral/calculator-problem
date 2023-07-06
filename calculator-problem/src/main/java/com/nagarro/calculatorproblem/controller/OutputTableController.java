package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.OutputTable;
import com.nagarro.calculatorproblem.service.impl.OutputTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OutputTableController {
    @Autowired
    private OutputTableServiceImpl service;

    @GetMapping("/getAllOutputTable")
    public List<OutputTable> getAllOutputTable() {
        return service.getAllOutputTable();
    }
    @GetMapping("/getAllVariable")
    public List<String> getAllVariable() {
        return service.getAllVariable();
    }

    @GetMapping("/getOutputTable/{companyName}")
    public OutputTable getOutputTable(@PathVariable String companyName) {
        return service.getOutputTable(companyName);
    }


    @GetMapping("/calculateOutputTable")
    public void calculateOutputTable() {
        service.calculateOutputTable();
    }
}
