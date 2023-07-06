package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.RiskCalcLogic;
import com.nagarro.calculatorproblem.repo.RiskCalcLogicRepository;
import com.nagarro.calculatorproblem.service.impl.RiskCalcLogicServiceImpl;
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
public class RiskCalcLogicServiceTest {
    @MockBean
    private RiskCalcLogicRepository repository;
    @Autowired
    private RiskCalcLogicServiceImpl service;

    private final List<RiskCalcLogic> riskCalcLogicList = Arrays.asList(
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

    private final RiskCalcLogic riskCalcLogic =new RiskCalcLogic("information_security_weight_score",
            "information_security*information_security_weight/100");

    @Test
    public void testGetAllRiskCalcLogic() {
        when(repository.findAll()).thenReturn(riskCalcLogicList);
        assertEquals(riskCalcLogicList, service.getAllRiskCalcLogic());
    }

    @Test
    public void testGetRiskCalcLogic() {
        when(repository.findById("information_security_weight_score")).thenReturn(Optional.of(riskCalcLogic));
        assertEquals(riskCalcLogic, service.getRiskCalcLogic("information_security_weight_score"));
    }

    @Test
    public void testAddRiskCalcLogic() {
        when(repository.save(riskCalcLogic)).thenReturn(riskCalcLogic);
        assertEquals(riskCalcLogic, service.addRiskCalcLogic(riskCalcLogic));
    }

    @Test
    public void testUpdateRiskCalcLogic() {
        when(repository.save(riskCalcLogic)).thenReturn(riskCalcLogic);
        when(repository.findById("information_security_weight_score")).thenReturn(Optional.of(riskCalcLogic));
        assertEquals(riskCalcLogic, service.addRiskCalcLogic(riskCalcLogic));
    }

    @Test
    public void testDeleteRiskCalcLogic() {
        doNothing().when(repository).deleteById("information_security_weight_score");
        repository.deleteById("information_security_weight_score");
    }

}
