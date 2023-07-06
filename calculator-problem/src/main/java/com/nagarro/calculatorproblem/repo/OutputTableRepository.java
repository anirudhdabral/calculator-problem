package com.nagarro.calculatorproblem.repo;

import com.nagarro.calculatorproblem.model.OutputTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputTableRepository extends JpaRepository<OutputTable, String> {

}
