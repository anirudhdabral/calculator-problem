package com.nagarro.calculatorproblem.repo;

import com.nagarro.calculatorproblem.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobStatusRepository extends JpaRepository<JobStatus, Integer> {
}
