package com.nagarro.calculatorproblem.service;

import com.nagarro.calculatorproblem.model.RiskScoreLevel;

import java.util.List;

public interface RiskScoreLevelService {
    List<RiskScoreLevel> getAllRiskScoreLevel();

    RiskScoreLevel getRiskScoreLevel(String score);

    RiskScoreLevel addRiskScoreLevel(RiskScoreLevel riskScoreLevel);

    RiskScoreLevel updateRiskScoreLevel(RiskScoreLevel riskScoreLevel);

    void deleteRiskScoreLevel(String score);
}
