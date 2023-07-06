package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.JobStatus;
import com.nagarro.calculatorproblem.repo.JobStatusRepository;
import com.nagarro.calculatorproblem.service.JobStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobStatusServiceImpl implements JobStatusService {
    @Autowired
    private JobStatusRepository repository;
    @Override
    public List<JobStatus> getAllJobStatus() {
        return repository.findAll();
    }
}
