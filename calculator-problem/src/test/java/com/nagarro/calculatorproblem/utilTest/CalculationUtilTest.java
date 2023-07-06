package com.nagarro.calculatorproblem.utilTest;

import com.nagarro.calculatorproblem.model.*;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.model.emenddable.OutputValues;
import com.nagarro.calculatorproblem.repo.*;
import com.nagarro.calculatorproblem.util.CalculationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculationUtilTest {
    @MockBean
    private CompanyRiskScoreRepository repoCompanyRiskScore;
    @MockBean
    private RiskDimensionRepository repoRiskDimension;
    @MockBean
    private RiskCalcLogicRepository repoRiskCalcLogic;
    @MockBean
    private RiskScoreLevelRepository repoRiskScoreLevel;
    @MockBean
    private ScoreCapRepository repoScoreCap;
    @MockBean
    private OutputTableRepository repoOutput;
    @MockBean
    private JobStatusRepository repoJobStatus;

    @Autowired
    CalculationUtil calculationUtil;

    private final List<CompanyRiskScore> companyRiskScoreList = Arrays.asList(
            new CompanyRiskScore("TCS", Arrays.asList(
                    new Dimensions("information_security", 80)
            )));

    private final List<RiskDimension> riskDimensionList = Arrays.asList(
            new RiskDimension("information_security_weight", 40.0)
    );
    private final List<RiskScoreLevel> riskScoreLevelList = Arrays.asList(
            new RiskScoreLevel("21-40", "high_risk")
    );
    private final List<RiskCalcLogic> riskCalcLogicList = Arrays.asList(
            new RiskCalcLogic("information_security_weight_score",
                    "information_security*information_security_weight/100"),
            new RiskCalcLogic("composite_risk_score",
                    "min(100,total_risk_capped_score)")
    );

    private final List<OutputTable> outputTableList = Arrays.asList(
            new OutputTable("TCS", Arrays.asList(
                    new OutputValues("information_security_weight_score", 32),
                    new OutputValues("total_risk_capped_score", 60),
                    new OutputValues("composite_risk_score", 60)
            ))
    );

    private final List<String> variableList = Arrays.asList(
            "information_security_weight",
            "information_security_weight_score",
            "composite_risk_score",
            "information_security"
    );


    @Test
    public void testCalculateOutputTable() {
        when(repoCompanyRiskScore.findAll()).thenReturn(companyRiskScoreList);

        when(repoRiskDimension.findAll()).thenReturn(riskDimensionList);

        when(repoRiskScoreLevel.findAll()).thenReturn(riskScoreLevelList);

        when(repoRiskCalcLogic.findAll()).thenReturn(riskCalcLogicList);

        when(repoRiskDimension.findById("information_security")).thenReturn(
                Optional.of(new RiskDimension("information_security", 40))
        );

        when(repoRiskCalcLogic.findById("information_security_weight_score")).thenReturn(
                Optional.of(new RiskCalcLogic("information_security_weight_score",
                        "information_security*information_security_weight/100"))
        );

        when(repoScoreCap.findById("one_high_risk")).thenReturn(
                Optional.of(new ScoreCap("one_high_risk", 60))
        );


        calculationUtil.calculateOutputTable();
        assertEquals(outputTableList, calculationUtil.getTestOutputTable());
    }

    @Test
    public void testGetAllVariable() {
        when(repoCompanyRiskScore.findAll()).thenReturn(companyRiskScoreList);
        when(repoRiskDimension.findAll()).thenReturn(riskDimensionList);
        when(repoRiskCalcLogic.findAll()).thenReturn(riskCalcLogicList);
        assertEquals(variableList, calculationUtil.getAllVariable());
    }
}
