package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.CompanyRiskScore;
import com.nagarro.calculatorproblem.model.RiskDimension;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.repo.CompanyRiskScoreRepository;
import com.nagarro.calculatorproblem.repo.RiskDimensionRepository;
import com.nagarro.calculatorproblem.service.impl.CompanyRiskScoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CompanyRiskScoreServiceTest {
    @MockBean
    private CompanyRiskScoreRepository repoCompanyRiskScore;
    @MockBean
    private RiskDimensionRepository repoRiskDimension;
    @Autowired
    private CompanyRiskScoreServiceImpl service;

    private final List<CompanyRiskScore> companyRiskScoreList = Arrays.asList(
            new CompanyRiskScore("TCS", Arrays.asList(
                    new Dimensions("information_security", 80),
                    new Dimensions("resilience", 60),
                    new Dimensions("conduct", 70)
            )),
            new CompanyRiskScore("Infosys", Arrays.asList(
                    new Dimensions("information_security", 90),
                    new Dimensions("resilience", 50),
                    new Dimensions("conduct", 55)
            ))
    );

    private final CompanyRiskScore companyRiskScore = new CompanyRiskScore("TCS", Arrays.asList(
            new Dimensions("information_security", 80),
            new Dimensions("resilience", 60),
            new Dimensions("conduct", 70)
    ));

    @Test
    public void testGetAllCompanyRiskScore() {
        when(repoCompanyRiskScore.findAll()).thenReturn(companyRiskScoreList);
        assertEquals(companyRiskScoreList, service.getAllCompanyRiskScore());
    }

    @Test
    public void testGetCompanyRiskScore() {
        when(repoCompanyRiskScore.findById("TCS")).thenReturn(Optional.of(companyRiskScore));
        assertEquals(companyRiskScore, service.getCompanyRiskScore("TCS"));
    }

    @Test
    public void testAddCompanyRiskScore() {
        when(repoCompanyRiskScore.save(companyRiskScore)).thenReturn(companyRiskScore);
        assertEquals(companyRiskScore, service.addCompanyRiskScore(companyRiskScore));
        verify(repoRiskDimension,atLeastOnce()).findById(any(String.class));
    }

    @Test
    public void testUpdateCompanyRiskScore() {
        when(repoCompanyRiskScore.save(companyRiskScore)).thenReturn(companyRiskScore);
        when(repoCompanyRiskScore.findById("TCS")).thenReturn(Optional.of(companyRiskScore));
        assertEquals(companyRiskScore, service.updateCompanyRiskScore(companyRiskScore));
    }

    @Test
    public void testDeleteCompanyRiskScore() {
        doNothing().when(repoCompanyRiskScore).deleteById("TCS");
        repoCompanyRiskScore.deleteById("TCS");
        verify(repoCompanyRiskScore).deleteById("TCS");
    }
}
