package com.nagarro.calculatorproblem.service.impl;

import com.nagarro.calculatorproblem.model.ScoreCap;
import com.nagarro.calculatorproblem.repo.ScoreCapRepository;
import com.nagarro.calculatorproblem.service.ScoreCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScoreCapServiceImpl implements ScoreCapService {
    @Autowired
    private ScoreCapRepository repository;

    @Override
    public List<ScoreCap> getAllScoreCap() {
        return repository.findAll();
    }

    @Override
    public ScoreCap getScoreCap(String condition) {
        return repository.findById(condition).orElse(null);
    }

    @Override
    public ScoreCap addScoreCap(ScoreCap scoreCap) {
        return repository.save(scoreCap);
    }

    @Override
    public ScoreCap updateScoreCap(ScoreCap scoreCap) {
        ScoreCap existingScoreCap = repository.findById(scoreCap.getCondition())
                .orElseThrow(NoSuchElementException::new);
        existingScoreCap.setTotalRiskCappedScore(scoreCap.getTotalRiskCappedScore());
        return repository.save(existingScoreCap);
    }

    @Override
    public void deleteScoreCap(String condition) {
        repository.deleteById(condition);
    }
}
