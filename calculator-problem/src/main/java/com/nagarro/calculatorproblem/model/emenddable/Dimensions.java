package com.nagarro.calculatorproblem.model.emenddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Dimensions {
    @Column(name = "dimension")
    private String dimensionName;

    @Column(name = "dimension_value")
    private double dimensionValue;
}
