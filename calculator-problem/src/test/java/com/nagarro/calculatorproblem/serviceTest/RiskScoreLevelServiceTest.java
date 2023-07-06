package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.RiskScoreLevel;
import com.nagarro.calculatorproblem.repo.RiskScoreLevelRepository;
import com.nagarro.calculatorproblem.service.impl.RiskScoreLevelServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RiskScoreLevelServiceTest {
    @MockBean
    private RiskScoreLevelRepository repository;
    @Autowired
    private RiskScoreLevelServiceImpl service;

    private final List<RiskScoreLevel> riskScoreLevelList = Arrays.asList(
            new RiskScoreLevel("81-100", "very_low_risk"),
            new RiskScoreLevel("61-80", "low_risk"),
            new RiskScoreLevel("41-60", "intermediate"),
            new RiskScoreLevel("21-40", "high_risk"),
            new RiskScoreLevel("01-20", "very_high_risk")
    );

    private final RiskScoreLevel riskScoreLevel = new RiskScoreLevel("21-40", "high_risk");

    @Test
    public void testGetAllRiskScoreLevel() {
        when(repository.findAll()).thenReturn(riskScoreLevelList);
        assertEquals(riskScoreLevelList, service.getAllRiskScoreLevel());
    }

    @Test
    public void testGetRiskScoreLevel() {
        when(repository.findById("21-40")).thenReturn(Optional.of(riskScoreLevel));
        assertEquals(riskScoreLevel, service.getRiskScoreLevel("21-40"));
    }

    @Test
    public void testAddRiskScoreLevel() {
        when(repository.save(riskScoreLevel)).thenReturn(riskScoreLevel);
        assertEquals(riskScoreLevel, service.addRiskScoreLevel(riskScoreLevel));
    }

    @Test
    public void testUpdateRiskScoreLevel() {
        when(repository.save(riskScoreLevel)).thenReturn(riskScoreLevel);
        when(repository.findById("21-40")).thenReturn(Optional.of(riskScoreLevel));
        assertEquals(riskScoreLevel, service.addRiskScoreLevel(riskScoreLevel));
    }

    @Test
    public void testDeleteRiskScoreLevel() {
        doNothing().when(repository).deleteById("21-40");
        repository.deleteById("21-40");
    }
}
