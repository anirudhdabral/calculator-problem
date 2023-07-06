package com.nagarro.calculatorproblem.service;

import com.nagarro.calculatorproblem.model.RiskDimension;

import java.util.List;

public interface RiskDimensionService {
    List<RiskDimension> getAllRiskDimension();

    RiskDimension getRiskDimension(String dimension);

    RiskDimension addRiskDimension(RiskDimension riskDimension);

    List<RiskDimension> rebalanceWeight(List<RiskDimension> riskDimensionList);

    RiskDimension updateRiskDimension(RiskDimension riskDimension);

    void deleteRiskDimension(String dimension);
}
