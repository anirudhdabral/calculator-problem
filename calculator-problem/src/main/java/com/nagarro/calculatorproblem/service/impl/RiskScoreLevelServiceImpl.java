package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.RiskScoreLevel;
import com.nagarro.calculatorproblem.repo.RiskScoreLevelRepository;
import com.nagarro.calculatorproblem.service.RiskScoreLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RiskScoreLevelServiceImpl implements RiskScoreLevelService {
    @Autowired
    private RiskScoreLevelRepository repository;

    @Override
    public List<RiskScoreLevel> getAllRiskScoreLevel() {
        return repository.findAll();
    }

    @Override
    public RiskScoreLevel getRiskScoreLevel(String score) {
        return repository.findById(score).orElse(null);
    }

    @Override
    public RiskScoreLevel addRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
        return repository.save(riskScoreLevel);
    }

    @Override
    public RiskScoreLevel updateRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
        RiskScoreLevel existingRiskScoreLevel = repository.findById(riskScoreLevel.getScore())
                .orElseThrow(NoSuchElementException::new);
        existingRiskScoreLevel.setLevel(riskScoreLevel.getLevel());
        return repository.save(existingRiskScoreLevel);
    }

    @Override
    public void deleteRiskScoreLevel(String score) {
        repository.deleteById(score);
    }
}
