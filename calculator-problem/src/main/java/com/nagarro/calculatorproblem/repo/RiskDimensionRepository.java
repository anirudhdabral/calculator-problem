package com.nagarro.calculatorproblem.repo;

import com.nagarro.calculatorproblem.model.RiskDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskDimensionRepository extends JpaRepository<RiskDimension, String> {

}
