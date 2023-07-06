package com.nagarro.calculatorproblem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculatorproblem.controller.OutputTableController;
import com.nagarro.calculatorproblem.model.OutputTable;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.model.emenddable.OutputValues;
import com.nagarro.calculatorproblem.service.impl.OutputTableServiceImpl;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(OutputTableController.class)
public class OutputTableControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OutputTableServiceImpl service;

    @Test
    public void testGetAllOutputTableAPI() throws Exception {
        List<OutputTable> outputTableList = Arrays.asList(
                new OutputTable("TCS", Arrays.asList(
                        new OutputValues("information_security_weight_score", 32.0),
                        new OutputValues("conduct_weight_score", 9.1)
                ))
        );
        when(service.getAllOutputTable()).thenReturn(outputTableList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllOutputTable")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].companyName", is("TCS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].values[0].element", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].values[0].value", is(32.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].values[1].element", is("conduct_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].values[1].value", is(9.1)));
        verify(service).getAllOutputTable();
    }

    @Test
    public void testGetAllVariableAPI() throws Exception {
        List<String> variableList = Arrays.asList("information_security_weight",
                "information_security_weight_score",
                "composite_risk_score",
                "information_security");
        when(service.getAllVariable()).thenReturn(variableList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllVariable")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]", is("information_security_weight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1]", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2]", is("composite_risk_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[3]", is("information_security")));
        verify(service).getAllVariable();
    }

    @Test
    public void testGetOutputTableAPI() throws Exception {
        OutputTable outputTable =
                new OutputTable("TCS", Arrays.asList(
                        new OutputValues("information_security_weight_score", 32.0),
                        new OutputValues("conduct_weight_score", 9.1)
                ));
        when(service.getOutputTable("TCS")).thenReturn(outputTable);
        mockMvc.perform(MockMvcRequestBuilders.get("/getOutputTable/{companyName}", "TCS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName", is("TCS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.values[0].element", is("information_security_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.values[0].value", is(32.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.values[1].element", is("conduct_weight_score")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.values[1].value", is(9.1)));
        verify(service).getOutputTable("TCS");
    }

    @Test
    public void testCalculateOutputTableAPI() throws Exception {
        doNothing().when(service).calculateOutputTable();
        mockMvc.perform(MockMvcRequestBuilders.get("/calculateOutputTable")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).calculateOutputTable();
    }


}
