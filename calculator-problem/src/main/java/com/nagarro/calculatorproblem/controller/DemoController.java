package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.*;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private CompanyRiskScoreRepository repoCRS;

    List<CompanyRiskScore> lsCRS = Arrays.asList(
            new CompanyRiskScore("TCS", Arrays.asList(
                    new Dimensions("information_security", 80),
                    new Dimensions("resilience", 60),
                    new Dimensions("conduct", 70)
            )),
            new CompanyRiskScore("Infosys", Arrays.asList(
                    new Dimensions("information_security", 90),
                    new Dimensions("resilience", 50),
                    new Dimensions("conduct", 55)
            )),
            new CompanyRiskScore("Wipro", Arrays.asList(
                    new Dimensions("information_security", 75),
                    new Dimensions("resilience", 65),
                    new Dimensions("conduct", 85)
            )),
            new CompanyRiskScore("Nagarro", Arrays.asList(
                    new Dimensions("information_security", 83),
                    new Dimensions("resilience", 81),
                    new Dimensions("conduct", 78)
            )),
            new CompanyRiskScore("CreditSuisse", Arrays.asList(
                    new Dimensions("information_security", 50),
                    new Dimensions("resilience", 40),
                    new Dimensions("conduct", 30)
            ))
    );

    @Autowired
    private RiskDimensionRepository repoRD;
    List<RiskDimension> lsRD = Arrays.asList(
            new RiskDimension("information_security_weight", 40.0),
            new RiskDimension("resilience_weight", 47.0),
            new RiskDimension("conduct_weight", 13.0)
    );

    @Autowired
    private RiskCalcLogicRepository repoRCL;
    List<RiskCalcLogic> lsRCL = Arrays.asList(
            new RiskCalcLogic("information_security_weight_score",
                    "information_security*information_security_weight/100"),
            new RiskCalcLogic("resilience_weight_score",
                    "resilience*resilience_weight/100"),
            new RiskCalcLogic("conduct_weight_score",
                    "conduct*conduct_weight/100"),
            new RiskCalcLogic("total_risk_score",
                    "information_security_weight_score+resilience_weight_score+conduct_weight_score"),
            new RiskCalcLogic("composite_risk_score",
                    "min(total_risk_score,total_risk_capped_score)")
    );

    @Autowired
    private RiskScoreLevelRepository repoRSL;
    List<RiskScoreLevel> lsRSL = Arrays.asList(
            new RiskScoreLevel("81-100", "very_low_risk"),
            new RiskScoreLevel("61-80", "low_risk"),
            new RiskScoreLevel("41-60", "intermediate"),
            new RiskScoreLevel("21-40", "high_risk"),
            new RiskScoreLevel("01-20", "very_high_risk")
    );

    @Autowired
    private ScoreCapRepository repoSCR;
    List<ScoreCap> lsSC = Arrays.asList(
            new ScoreCap("one_very_high_risk", 40),
            new ScoreCap("two_very_high_risk", 30),
            new ScoreCap("one_high_risk", 60),
            new ScoreCap("two_high_risk", 50)
    );

    @RequestMapping("/resetDatabase")
    public void resetDatabase() {
        repoCRS.deleteAll();
        repoRD.deleteAll();
        repoRCL.deleteAll();
        repoRSL.deleteAll();
        repoSCR.deleteAll();
        //
        repoCRS.saveAll(lsCRS);
        repoRD.saveAll(lsRD);
        repoRCL.saveAll(lsRCL);
        repoRSL.saveAll(lsRSL);
        repoSCR.saveAll(lsSC);
    }

}
