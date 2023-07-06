package com.nagarro.calculatorproblem.model;

import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_risk_score")
public class CompanyRiskScore {

    @Id
    @Column(name = "company_name")
    private String companyName;

    @ElementCollection
    @CollectionTable(name = "dimensions", joinColumns = @JoinColumn(name = "company_name"))
    @Column(name = "dimensions")
    private List<Dimensions> dimensions = new ArrayList<>();

}
