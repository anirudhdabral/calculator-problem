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
public class OutputValues {
    @Column(name = "element_name")
    private String element;

    @Column(name = "element_value")
    private double value;
}
