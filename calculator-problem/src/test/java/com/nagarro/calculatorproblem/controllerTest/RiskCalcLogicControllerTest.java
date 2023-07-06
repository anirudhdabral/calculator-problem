package com.nagarro.calculatorproblem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculatorproblem.controller.RiskCalcLogicController;
import com.nagarro.calculatorproblem.controller.RiskCalcLogicController;
import com.nagarro.calculatorproblem.model.RiskCalcLogic;
import com.nagarro.calculatorproblem.model.RiskCalcLogic;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.service.impl.RiskCalcLogicServiceImpl;
import com.nagarro.calculatorproblem.service.impl.RiskCalcLogicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RiskCalcLogicController.class)
public class RiskCalcLogicControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RiskCalcLogicServiceImpl service;

    @Test
    public void testGetAllRiskCalcLogicAPI() throws Exception {
        List<RiskCalcLogic> riskCalcLogicList = Arrays.asList(
                new RiskCalcLogic("information_security_weight_score",
                        "information_security*information_security_weight/100")
        );
        when(service.getAllRiskCalcLogic()).thenReturn(riskCalcLogicList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllRiskCalcLogic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].elementName", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].formula", is("information_security*information_security_weight/100")));
        verify(service).getAllRiskCalcLogic();
    }

    @Test
    public void testGetRiskCalcLogicAPI() throws Exception {
        RiskCalcLogic riskCalcLogic =
                new RiskCalcLogic("information_security_weight_score",
                        "information_security*information_security_weight/100");
        when(service.getRiskCalcLogic("information_security_weight_score")).thenReturn(riskCalcLogic);
        mockMvc.perform(MockMvcRequestBuilders.get("/getRiskCalcLogic/{elementName}", "information_security_weight_score")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.elementName", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.formula", is("information_security*information_security_weight/100")));
        verify(service).getRiskCalcLogic("information_security_weight_score");
    }

    @Test
    public void testAddRiskCalcLogic() throws Exception {
        RiskCalcLogic riskCalcLogic =
                new RiskCalcLogic("information_security_weight_score",
                        "information_security*information_security_weight/100");
        String requestBody = objectMapper.writeValueAsString(riskCalcLogic);
        when(service.addRiskCalcLogic(riskCalcLogic)).thenReturn(riskCalcLogic);
        mockMvc.perform(MockMvcRequestBuilders.post("/addRiskCalcLogic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.elementName", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.formula", is("information_security*information_security_weight/100")));
        verify(service).addRiskCalcLogic(any(RiskCalcLogic.class));
    }

    @Test
    public void testUpdateRiskCalcLogic() throws Exception {
        RiskCalcLogic riskCalcLogic =new RiskCalcLogic("information_security_weight_score",
                "information_security*conduct_weight/100");
        String requestBody = objectMapper.writeValueAsString(riskCalcLogic);
        when(service.updateRiskCalcLogic(riskCalcLogic)).thenReturn(riskCalcLogic);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateRiskCalcLogic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.elementName", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.formula", is("information_security*conduct_weight/100")));;
        verify(service).updateRiskCalcLogic(any(RiskCalcLogic.class));
    }

    @Test
    public void testDeleteRiskCalcLogic() throws Exception {
        doNothing().when(service).deleteRiskCalcLogic("information_security_weight_score");
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteRiskCalcLogic/{elementName}","information_security_weight_score")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).deleteRiskCalcLogic("information_security_weight_score");
    }
}
