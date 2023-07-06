package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.OutputTable;
import com.nagarro.calculatorproblem.repo.OutputTableRepository;
import com.nagarro.calculatorproblem.service.OutputTableService;
import com.nagarro.calculatorproblem.util.CalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputTableServiceImpl implements OutputTableService {
    @Autowired
    private OutputTableRepository repository;
    @Autowired
    private CalculationUtil calculationUtil;

    @Override
    public List<OutputTable> getAllOutputTable() {
        if (repository.findAll().size() == 0) {
            calculationUtil.calculateOutputTable();
        }
        return repository.findAll();
    }
    @Override
    public OutputTable getOutputTable(String companyName) {
        return repository.findById(companyName).orElse(null);
    }
    @Override
    public List<String> getAllVariable() {
        return calculationUtil.getAllVariable();
    }
    @Override
    public void calculateOutputTable(){
        calculationUtil.calculateOutputTable();
    }

}
