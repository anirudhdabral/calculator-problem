package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.CompanyRiskScore;
import com.nagarro.calculatorproblem.model.RiskDimension;
import com.nagarro.calculatorproblem.repo.CompanyRiskScoreRepository;
import com.nagarro.calculatorproblem.repo.RiskDimensionRepository;
import com.nagarro.calculatorproblem.service.CompanyRiskScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompanyRiskScoreServiceImpl implements CompanyRiskScoreService {
    @Autowired
    private CompanyRiskScoreRepository repoCompanyRiskScore;

    @Autowired
    private RiskDimensionRepository repoRiskDimension;

    @Override
    public List<CompanyRiskScore> getAllCompanyRiskScore() {
        return repoCompanyRiskScore.findAll();
    }

    @Override
    public CompanyRiskScore getCompanyRiskScore(String companyName) {
        return repoCompanyRiskScore.findById(companyName).orElse(null);
    }

    @Override
    public CompanyRiskScore addCompanyRiskScore(CompanyRiskScore companyRiskScore) {
        companyRiskScore.getDimensions().forEach(dimension -> {
            if (repoRiskDimension.findById(dimension.getDimensionName()).orElse(null) == null) {
                repoRiskDimension.save(new RiskDimension(dimension.getDimensionName(), 0));
            }
        });
        return repoCompanyRiskScore.save(companyRiskScore);
    }

    @Override
    public CompanyRiskScore updateCompanyRiskScore(CompanyRiskScore companyRiskScore) {
        CompanyRiskScore existingCompanyRiskScore = repoCompanyRiskScore
                .findById(companyRiskScore.getCompanyName())
                .orElseThrow(NoSuchElementException::new);
        existingCompanyRiskScore.setDimensions(companyRiskScore.getDimensions());
        return repoCompanyRiskScore.save(existingCompanyRiskScore);
    }

    @Override
    public void deleteCompanyRiskScore(String companyName) {
        repoCompanyRiskScore.deleteById(companyName);
    }
}
