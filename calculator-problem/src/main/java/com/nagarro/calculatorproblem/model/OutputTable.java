package com.nagarro.calculatorproblem.model;

import com.nagarro.calculatorproblem.model.emenddable.OutputValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "output_table")
public class OutputTable {

    @Id
    @Column(name = "company_name")
    private String companyName;

    @ElementCollection
    @CollectionTable(name = "output_values", joinColumns = @JoinColumn(name = "company_name"))
    @Column(name = "output_values")
    private List<OutputValues> values;

}