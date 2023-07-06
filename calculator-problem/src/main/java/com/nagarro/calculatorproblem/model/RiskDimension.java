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
@Table(name = "risk_dimension")
public class RiskDimension {

    @Id
    @Column(name = "dimension")
    private String dimension;

    @Column(name = "weight")
    private double weight;

}
