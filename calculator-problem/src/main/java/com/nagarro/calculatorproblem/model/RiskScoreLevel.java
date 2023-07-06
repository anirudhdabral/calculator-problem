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
@Table(name = "risk_score_level")
public class RiskScoreLevel {
	
	@Id
	@Column(name = "score")
	private String score;
	
	@Column(name = "level")
	private String level;

}
