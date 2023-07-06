package com.nagarro.calculatorproblem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculatorproblem.controller.RiskScoreLevelController;
import com.nagarro.calculatorproblem.controller.RiskScoreLevelController;
import com.nagarro.calculatorproblem.model.RiskScoreLevel;
import com.nagarro.calculatorproblem.service.impl.RiskScoreLevelServiceImpl;
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
@WebMvcTest(RiskScoreLevelController.class)
public class RiskScoreLevelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RiskScoreLevelServiceImpl service;

    @Test
    public void testGetAllRiskScoreLevelAPI() throws Exception {
        List<RiskScoreLevel> riskScoreLevelList = Arrays.asList(
                new RiskScoreLevel("81-100", "very_low_risk"),
                new RiskScoreLevel("61-80", "low_risk")
        );
        when(service.getAllRiskScoreLevel()).thenReturn(riskScoreLevelList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllRiskScoreLevel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].score", is("81-100")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].level", is("very_low_risk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].score", is("61-80")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].level", is("low_risk")));
        verify(service).getAllRiskScoreLevel();
    }

    @Test
    public void testGetRiskScoreLevelAPI() throws Exception {
        RiskScoreLevel riskScoreLevel =new RiskScoreLevel("61-80", "low_risk");
        when(service.getRiskScoreLevel("61-80")).thenReturn(riskScoreLevel);
        mockMvc.perform(MockMvcRequestBuilders.get("/getRiskScoreLevel/{score}", "61-80")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", is("61-80")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level", is("low_risk")));
        verify(service).getRiskScoreLevel("61-80");
    }

    @Test
    public void testAddRiskScoreLevel() throws Exception {
        RiskScoreLevel riskScoreLevel =
                new RiskScoreLevel("61-80",
                        "low_risk");
        String requestBody = objectMapper.writeValueAsString(riskScoreLevel);
        when(service.addRiskScoreLevel(riskScoreLevel)).thenReturn(riskScoreLevel);
        mockMvc.perform(MockMvcRequestBuilders.post("/addRiskScoreLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", is("61-80")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level", is("low_risk")));
        verify(service).addRiskScoreLevel(any(RiskScoreLevel.class));
    }

    @Test
    public void testUpdateRiskScoreLevel() throws Exception {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel("61-80",
                "low_risk");
        String requestBody = objectMapper.writeValueAsString(riskScoreLevel);
        when(service.updateRiskScoreLevel(riskScoreLevel)).thenReturn(riskScoreLevel);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateRiskScoreLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", is("61-80")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level", is("low_risk")));
        ;
        verify(service).updateRiskScoreLevel(any(RiskScoreLevel.class));
    }

    @Test
    public void testDeleteRiskScoreLevel() throws Exception {
        doNothing().when(service).deleteRiskScoreLevel("61-80");
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteRiskScoreLevel/{score}", "61-80")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).deleteRiskScoreLevel("61-80");
    }
}
