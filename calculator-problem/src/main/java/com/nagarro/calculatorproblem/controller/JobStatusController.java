package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.JobStatus;
import com.nagarro.calculatorproblem.service.impl.JobStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobStatusController {
    @Autowired
    private JobStatusServiceImpl service;

    @GetMapping("/getAllJobStatus")
    public List<JobStatus> getAllJobStatus(){
        return service.getAllJobStatus();
    }
}
