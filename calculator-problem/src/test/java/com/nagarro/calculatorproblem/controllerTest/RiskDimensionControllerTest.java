package com.nagarro.calculatorproblem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculatorproblem.controller.RiskDimensionController;
import com.nagarro.calculatorproblem.model.RiskDimension;
import com.nagarro.calculatorproblem.service.impl.RiskDimensionServiceImpl;
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
@WebMvcTest(RiskDimensionController.class)
public class RiskDimensionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RiskDimensionServiceImpl service;

    @Test
    public void testGetAllRiskDimensionAPI() throws Exception {
        List<RiskDimension> riskDimensionList = Arrays.asList(
                new RiskDimension("information_security_weight", 40.0),
                new RiskDimension("resilience_weight", 47.0)
        );
        when(service.getAllRiskDimension()).thenReturn(riskDimensionList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllRiskDimension")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimension", is("information_security_weight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].weight", is(40.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].dimension", is("resilience_weight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].weight", is(47.0)));
        verify(service).getAllRiskDimension();
    }

    @Test
    public void testRebalanceWeightAPI() throws Exception {
        List<RiskDimension> riskDimensionList = Arrays.asList(
                new RiskDimension("information_security_weight", 40.0),
                new RiskDimension("resilience_weight", 47.0)
        );
        String requestBody = objectMapper.writeValueAsString(riskDimensionList);
        when(service.rebalanceWeight(riskDimensionList)).thenReturn(riskDimensionList);
        mockMvc.perform(MockMvcRequestBuilders.put("/rebalanceWeight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimension", is("information_security_weight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].weight", is(40.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].dimension", is("resilience_weight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].weight", is(47.0)))
                ;
        verify(service).rebalanceWeight(riskDimensionList);
    }


}

