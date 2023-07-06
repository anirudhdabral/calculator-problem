package com.nagarro.calculatorproblem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "risk_calc_logic")
public class RiskCalcLogic {

    @Id
    @Column(name = "element_name")
    private String elementName;

    @Column(name = "formula")
    private String formula;
}
