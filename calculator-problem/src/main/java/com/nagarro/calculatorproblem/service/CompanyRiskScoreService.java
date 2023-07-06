package com.nagarro.calculatorproblem.service;

import com.nagarro.calculatorproblem.model.CompanyRiskScore;

import java.util.List;

public interface CompanyRiskScoreService {
    List<CompanyRiskScore> getAllCompanyRiskScore();

    CompanyRiskScore getCompanyRiskScore(String companyName);

    CompanyRiskScore addCompanyRiskScore(CompanyRiskScore companyRiskScore);

    CompanyRiskScore updateCompanyRiskScore(CompanyRiskScore companyRiskScore);

    void deleteCompanyRiskScore(String companyName);
}
