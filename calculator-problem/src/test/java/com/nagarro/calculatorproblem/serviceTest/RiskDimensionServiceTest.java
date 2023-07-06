package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.RiskDimension;
import com.nagarro.calculatorproblem.repo.RiskDimensionRepository;
import com.nagarro.calculatorproblem.service.impl.RiskDimensionServiceImpl;
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
public class RiskDimensionServiceTest {
    @MockBean
    private RiskDimensionRepository repository;
    @Autowired
    private RiskDimensionServiceImpl service;

    private final List<RiskDimension> riskDimensionList = Arrays.asList(
            new RiskDimension("information_security_weight", 40.0),
            new RiskDimension("resilience_weight", 47.0),
            new RiskDimension("conduct_weight", 13.0)
    );

    private final RiskDimension riskDimension = new RiskDimension("conduct_weight", 13.0);

    @Test
    public void testGetAllRiskDimension() {
        when(repository.findAll()).thenReturn(riskDimensionList);
        assertEquals(riskDimensionList, service.getAllRiskDimension());
    }

    @Test
    public void testGetRiskDimension() {
        when(repository.findById("conduct_weight")).thenReturn(Optional.of(riskDimension));
        assertEquals(riskDimension, service.getRiskDimension("conduct_weight"));
    }

    @Test
    public void testRebalanceWeight() {
        when(repository.saveAll(riskDimensionList)).thenReturn(riskDimensionList);
        assertEquals(riskDimensionList, service.rebalanceWeight(riskDimensionList));
    }

    @Test
    public void testAddRiskDimension() {
        when(repository.save(riskDimension)).thenReturn(riskDimension);
        assertEquals(riskDimension, service.addRiskDimension(riskDimension));
    }

    @Test
    public void testUpdateRiskDimension() {
        when(repository.save(riskDimension)).thenReturn(riskDimension);
        when(repository.findById("conduct_weight")).thenReturn(Optional.of(riskDimension));
        assertEquals(riskDimension, service.addRiskDimension(riskDimension));
    }

    @Test
    public void testDeleteRiskDimension() {
        doNothing().when(repository).deleteById("conduct_weight");
        repository.deleteById("conduct_weight");
    }
}
