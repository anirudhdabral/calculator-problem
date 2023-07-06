package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.OutputTable;
import com.nagarro.calculatorproblem.model.emenddable.OutputValues;
import com.nagarro.calculatorproblem.repo.OutputTableRepository;
import com.nagarro.calculatorproblem.service.impl.OutputTableServiceImpl;
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
public class OutputTableServiceTest {
    @MockBean
    private OutputTableRepository outputTableRepository;
    @Autowired
    private OutputTableServiceImpl outputTableService;

    private final List<OutputTable> outputTableList = Arrays.asList(
            new OutputTable("TCS", Arrays.asList(
                    new OutputValues("conduct_weight_score", 9.1),
                    new OutputValues("information_security_weight_score", 32.0),
                    new OutputValues("total_risk_score", 69.3),
                    new OutputValues("total_risk_capped_score", 40.0),
                    new OutputValues("composite_risk_score", 40.0),
                    new OutputValues("resilience_weight_score", 28.2)
            )),
            new OutputTable("Nagarro", Arrays.asList(
                    new OutputValues("conduct_weight_score", 10.14),
                    new OutputValues("information_security_weight_score", 33.2),
                    new OutputValues("total_risk_score", 81.41),
                    new OutputValues("total_risk_capped_score", 40.0),
                    new OutputValues("composite_risk_score", 40.0),
                    new OutputValues("resilience_weight_score", 38.07)
            ))

    );

    private final OutputTable outputTable = new OutputTable("Nagarro", Arrays.asList(
            new OutputValues("conduct_weight_score", 10.14),
            new OutputValues("information_security_weight_score", 33.2),
            new OutputValues("total_risk_score", 81.41),
            new OutputValues("total_risk_capped_score", 40.0),
            new OutputValues("composite_risk_score", 40.0),
            new OutputValues("resilience_weight_score", 38.07)
    ));

    @Test
    public void testGetAllOutputTable() {
        when(outputTableRepository.findAll()).thenReturn(outputTableList);
        assertEquals(outputTableList, outputTableService.getAllOutputTable());
    }

    @Test
    public void testGetOutputTable() {
        when(outputTableRepository.findById("Nagarro")).thenReturn(Optional.of(outputTable));
        assertEquals(outputTable, outputTableService.getOutputTable("Nagarro"));
    }
}
