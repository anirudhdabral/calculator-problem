package com.nagarro.calculatorproblem.repo;

import com.nagarro.calculatorproblem.model.ScoreCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreCapRepository extends JpaRepository<ScoreCap, String> {

}
