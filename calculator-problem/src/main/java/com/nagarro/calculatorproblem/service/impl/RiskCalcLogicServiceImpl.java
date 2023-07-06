package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.RiskCalcLogic;
import com.nagarro.calculatorproblem.repo.RiskCalcLogicRepository;
import com.nagarro.calculatorproblem.service.RiskCalcLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RiskCalcLogicServiceImpl implements RiskCalcLogicService {
    @Autowired
    private RiskCalcLogicRepository repository;

    @Override
    public List<RiskCalcLogic> getAllRiskCalcLogic() {
        return repository.findAll();
    }

    @Override
    public RiskCalcLogic getRiskCalcLogic(String elementName) {
        return repository.findById(elementName).orElse(null);
    }

    @Override
    public RiskCalcLogic addRiskCalcLogic(RiskCalcLogic riskCalcLogic) {
        return repository.save(riskCalcLogic);
    }

    @Override
    public RiskCalcLogic updateRiskCalcLogic(RiskCalcLogic riskCalcLogic) {
        RiskCalcLogic existingRiskCalcLogic = repository.findById(riskCalcLogic.getElementName())
                .orElseThrow(NoSuchElementException::new);
        existingRiskCalcLogic.setFormula(riskCalcLogic.getFormula());
        return repository.save(existingRiskCalcLogic);
    }

    @Override
    public void deleteRiskCalcLogic(String elementName) {
        repository.deleteById(elementName);
    }
}
