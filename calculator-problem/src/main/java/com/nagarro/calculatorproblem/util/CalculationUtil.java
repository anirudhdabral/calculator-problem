package com.nagarro.calculatorproblem.util;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.nagarro.calculatorproblem.customException.ZeroDivisionErrorException;
import com.nagarro.calculatorproblem.model.*;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.model.emenddable.OutputValues;
import com.nagarro.calculatorproblem.repo.*;
import com.nagarro.calculatorproblem.service.impl.OutputTableServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CalculationUtil {
    private static List<OutputTable> testOutputTable = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(OutputTableServiceImpl.class);
    private static final String OPERATORS = "[-+*/]";
    private static final String PARENTHESIS = "[()]";
    private static final String COMMA = ",";
    private static final String TWO_DECIMALS = "%.2f";
    private static final long ONE_HOUR = 1000 * 60 * 60;
    private final Map<String, Double> outputValuesMap = new HashMap<>();

    @Autowired
    private CompanyRiskScoreRepository repoCompanyRiskScore;
    @Autowired
    private RiskDimensionRepository repoRiskDimension;
    @Autowired
    private RiskCalcLogicRepository repoRiskCalcLogic;
    @Autowired
    private RiskScoreLevelRepository repoRiskScoreLevel;
    @Autowired
    private ScoreCapRepository repoScoreCap;
    @Autowired
    private OutputTableRepository repoOutput;
    @Autowired
    private JobStatusRepository repoJobStatus;

    private int calculateTotalRiskCappedScore(List<Double> weightList) {
        // get all riskScores from risk_score_level table
        List<RiskScoreLevel> listRiskScoreLevel = repoRiskScoreLevel.findAll();
        List<String> riskList = weightList
                .stream()
                .flatMap(weight ->
                        // checks for match for each risk score level
                        listRiskScoreLevel
                                .stream()
                                // find first match
                                .filter(rsl -> {
                                    // split scores by "-", converting to integer and adding to list
                                    List<Integer> scoreRange = Arrays.stream(rsl.getScore().split("-"))
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());
                                    // get min and max range
                                    int minScore = scoreRange.get(0);
                                    int maxScore = scoreRange.get(1);
                                    // checks for match
                                    return weight >= minScore && weight <= maxScore;
                                })
                                // get level of the matched score
                                .map(RiskScoreLevel::getLevel)
                )
                .collect(Collectors.toList());
        // get list of all total risk capped score
        List<Integer> cappedScores = riskList
                .stream()
                // for unique risk
                .distinct()
                .map(risk -> {
                    // count frequency of risk in riskList
                    int count = Collections.frequency(riskList, risk);
                    // add prefix, if 1 then "one_", else if 2 then "two_", else ""
                    String prefix = (count == 1) ? "one_" : ((count == 2) ? "two_" : "");
                    // search scoreCap table for risk as (prefix + risk)
                    ScoreCap sc = repoScoreCap.findById(prefix + risk).orElse(null);
                    // if not found, return total_risk_capped_score else return -1
                    return sc != null ? sc.getTotalRiskCappedScore() : -1;
                })
                // remove all -1 scores
                .filter(score -> score != -1)
                .collect(Collectors.toList());
        // if no capped score is found, return 100 (as mentioned in problem statement)
        // else return minimum of cappedScores
        return cappedScores.isEmpty() ? 100 : Collections.min(cappedScores);
    }

    private double decodeFormula(String formula, List<Dimensions> dimensionsList) {
        // initialise evaluator and variables
        DoubleEvaluator eval = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        // extract variables present in formula to a list
        List<String> formulaVariables = Arrays.asList(formula.split(OPERATORS));
        // hashmap for variable and their values
        Map<String, Double> variableValueMap = new HashMap<>();
        boolean variableNotFound = false;
        // add name and values for each dimensions of the company to hashmap
        dimensionsList
                .forEach(d ->
                        variableValueMap.put(d.getDimensionName(), d.getDimensionValue())
                );
        // add name and their weight percentage for each dimensions to hashmap
        repoRiskDimension.findAll()
                .forEach(rd ->
                        variableValueMap.put(rd.getDimension(), rd.getWeight())
                );
        // try for if variables are present or not
        try {
            double totalRiskCappedScore =
                    // call method to calculate total_risk_capped_score
                    calculateTotalRiskCappedScore(
                            // passing all calculated dimension scores in a list as parameter
                            repoRiskDimension.findAll()
                                    .stream()
                                    //checks in output map if required variables are present or not
                                    .map(item -> outputValuesMap.get(item.getDimension() + "_score"))
                                    .filter(item -> item != null)
                                    .collect(Collectors.toList())
                    );
            // if found, add total_risk_capped_score to output map
            outputValuesMap.put("total_risk_capped_score", totalRiskCappedScore);
            System.out.println("Successfully calculated total risk capped score");
        } catch (NullPointerException e) {
            // to handle exception if variables are not found
            System.out.println("Values required to calculate are not available yet. Re-attempting...");
        }
        // checks for min() or max() case
        if (formula.toLowerCase().charAt(0) == 'm') {
            // split the formula by parenthesis
            List<String> minMaxList = Arrays.asList(formula.split(PARENTHESIS));
            // split the formula by comma minMaxList = ["min(" + "variable1,variable2,..." + ")"]
            formulaVariables = Arrays.asList(minMaxList.get(1).split(COMMA));
        }
        for (String variable : formulaVariables) {
            // confirms variable is not a number
            try {
                int number = Integer.parseInt(variable);
                if (number == 0) {
                    logger.error("Formula: '" + formula + "' may cause zero division error!");
                    throw new ZeroDivisionErrorException("Formula: '" + formula + "' may cause zero division error!");
                }
            } catch (ZeroDivisionErrorException e) {
                return -1;
            } catch (Exception e) {
                // checks variable map for the variable
                if (variableValueMap.containsKey(variable)) {
                    // sets variable for evaluating
                    variables.set(variable, variableValueMap.get(variable));
                }
                // checks output map for the variable
                else if (outputValuesMap.containsKey(variable)) {
                    variables.set(variable, outputValuesMap.get(variable));
                } else {
                    variableNotFound = true;
                }
            }
        }
        // returns -1 if any variable required is not found
        if (variableNotFound) {
            return -1;
        }
        // evaluate the result
        double result = eval.evaluate(formula, variables);
        // returns double with two decimal points
        return Double.parseDouble(String.format(TWO_DECIMALS, result));
    }

    @Scheduled(initialDelay = ONE_HOUR, fixedRate = ONE_HOUR)
    // scheduled to update the output table every hour
    @Transactional
    public void calculateOutputTable() {
        JobStatus jobStatus = new JobStatus();
        logger.info("Calculate method executed :: Execution time - {}", LocalDateTime.now());
        List<OutputTable> outputTableList = new ArrayList<>();
        // list of all companies
        repoCompanyRiskScore.findAll()
                .forEach(company -> {
                    // try for each company
                    try {
                        // to store output details of each company
                        OutputTable output = new OutputTable();
                        output.setCompanyName(company.getCompanyName());
                        // get all the formulas from risk_calc_logic table
                        List<RiskCalcLogic> riskCalcLogicList = repoRiskCalcLogic.findAll();
                        int iteration = 0;
                        do { // for each formula
                            riskCalcLogicList.forEach(riskCalcLogic -> {
                                // call decodeFormula method passing formula logic and company dimension list
                                double result = decodeFormula(riskCalcLogic.getFormula(), company.getDimensions());
                                // put calculated value to map which will be used to store values to output table
                                outputValuesMap.put(riskCalcLogic.getElementName(), result);
                            });
                            iteration++;
                        } while (outputValuesMap.containsValue(-1.0) && iteration < 10); // checks if any formula is not evaluated for 10 iterations
                        if (iteration >= 10 && outputValuesMap.containsValue(-1.0)) {
                            logger.info("Cannot calculate some formulas for company: '" + company.getCompanyName() + "' as the dimensions required for evaluating the formula are missing");
                            outputValuesMap.values().removeIf(value -> value == -1);
                        }
                        // convert hashmap values to list and save it to output list
                        List<OutputValues> outputValuesList = outputValuesMap
                                .entrySet()
                                .stream()
                                .map(item ->
                                        new OutputValues(item.getKey(), item.getValue())
                                )
                                .collect(Collectors.toList());
                        output.setValues(outputValuesList);
                        // add output to output list
                        outputTableList.add(output);
                        // clear the map for next company
                        outputValuesMap.clear();
                        // add logger message
                        logger.info("successfully calculated for company: " + company.getCompanyName());
                        // sets job status for current company
                        jobStatus.setOutputStatus("SUCCESS");
                    } catch (Exception e) {
                        // adds logging error message
                        logger.error("exception caught: " + e.getMessage());
                        // sets job status and error for current company
                        jobStatus.setJobError("Failed for the company: " + company.getCompanyName());
                    }
                });
        if (outputTableList.size() > 0) {
            // replaces old data with new one in the database
            repoOutput.deleteAll();
            repoOutput.saveAll(outputTableList);
            logger.info("updated database");
        } else {
            jobStatus.setJobError("No valid company risk score data available");
        }
        // save job status for current company to the database and job date and time
        jobStatus.setJobDateTime(LocalDateTime.now());
        repoJobStatus.save(jobStatus);

        // for testing
        testOutputTable = outputTableList;
    }

    public List<OutputTable> getTestOutputTable() {
        return testOutputTable;
    }

    public List<String> getAllVariable() {
        List<String> variableList = new ArrayList<>();
        repoRiskDimension.findAll()
                .forEach(riskDimension ->
                        variableList.add(riskDimension.getDimension())
                );
        repoRiskCalcLogic.findAll()
                .forEach(riskCalcLogic ->
                        variableList.add(riskCalcLogic.getElementName())
                );
        repoCompanyRiskScore.findAll().forEach(companyRiskScore -> {
            companyRiskScore.getDimensions()
                    .forEach(dimension -> {
                        String dimensionName = dimension.getDimensionName();
                        if (!variableList.contains(dimensionName)) {
                            variableList.add(dimensionName);
                        }
                    });
        });
        return variableList;
    }
}
