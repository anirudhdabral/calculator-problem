package com.nagarro.calculatorproblem.service;

import com.nagarro.calculatorproblem.model.ScoreCap;

import java.util.List;

public interface ScoreCapService {
    List<ScoreCap> getAllScoreCap();

    ScoreCap getScoreCap(String condition);

    ScoreCap addScoreCap(ScoreCap scoreCap);

    ScoreCap updateScoreCap(ScoreCap scoreCap);

    void deleteScoreCap(String condition);
}
