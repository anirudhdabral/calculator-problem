package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.RiskDimension;
import com.nagarro.calculatorproblem.repo.RiskDimensionRepository;
import com.nagarro.calculatorproblem.service.RiskDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RiskDimensionServiceImpl implements RiskDimensionService {
    @Autowired
    private RiskDimensionRepository repository;

    @Override
    public List<RiskDimension> getAllRiskDimension() {
        return repository.findAll();
    }

    @Override
    public RiskDimension getRiskDimension(String dimension) {
        return repository.findById(dimension).orElse(null);
    }

    @Override
    public RiskDimension addRiskDimension(RiskDimension riskDimension) {
        return repository.save(riskDimension);
    }

    @Override
    public List<RiskDimension> rebalanceWeight(List<RiskDimension> riskDimensionList) {
        return repository.saveAll(riskDimensionList);
    }

    @Override
    public RiskDimension updateRiskDimension(RiskDimension riskDimension) {
        RiskDimension existingRiskDimension = repository.findById(riskDimension.getDimension())
                .orElseThrow(NoSuchElementException::new);
        existingRiskDimension.setWeight(riskDimension.getWeight());
        return repository.save(existingRiskDimension);
    }

    @Override
    public void deleteRiskDimension(String dimension) {
        repository.deleteById(dimension);
    }
}
