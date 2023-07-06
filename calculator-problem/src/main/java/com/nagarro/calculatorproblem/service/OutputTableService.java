package com.nagarro.calculatorproblem.service;

import com.nagarro.calculatorproblem.model.OutputTable;

import java.util.List;

public interface OutputTableService {
    List<OutputTable> getAllOutputTable();
    OutputTable getOutputTable(String companyName);
    void calculateOutputTable();
    List<String> getAllVariable();
}
