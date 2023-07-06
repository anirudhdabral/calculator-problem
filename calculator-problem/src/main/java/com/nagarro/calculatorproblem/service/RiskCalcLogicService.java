package com.nagarro.calculatorproblem.service;

import com.nagarro.calculatorproblem.model.RiskCalcLogic;

import java.util.List;

public interface RiskCalcLogicService {
    List<RiskCalcLogic> getAllRiskCalcLogic();

    RiskCalcLogic getRiskCalcLogic(String elementName);

    RiskCalcLogic addRiskCalcLogic(RiskCalcLogic riskCalcLogic);

    RiskCalcLogic updateRiskCalcLogic(RiskCalcLogic riskCalcLogic);

    void deleteRiskCalcLogic(String elementName);
}
