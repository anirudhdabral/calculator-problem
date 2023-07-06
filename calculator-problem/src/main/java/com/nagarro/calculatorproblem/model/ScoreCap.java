package com.nagarro.calculatorproblem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "score_cap")
public class ScoreCap {
	
	@Id
	@Column(name = "risk_condition")
	private String condition;
	
	@Column(name = "total_risk_capped_score")
	private int totalRiskCappedScore;

}
