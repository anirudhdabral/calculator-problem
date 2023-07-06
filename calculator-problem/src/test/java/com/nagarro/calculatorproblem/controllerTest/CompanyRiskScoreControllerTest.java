package com.nagarro.calculatorproblem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculatorproblem.controller.CompanyRiskScoreController;
import com.nagarro.calculatorproblem.model.CompanyRiskScore;
import com.nagarro.calculatorproblem.model.emenddable.Dimensions;
import com.nagarro.calculatorproblem.service.impl.CompanyRiskScoreServiceImpl;
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
@WebMvcTest(CompanyRiskScoreController.class)
public class CompanyRiskScoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CompanyRiskScoreServiceImpl service;

    @Test
    public void testGetAllCompanyRiskScoreAPI() throws Exception {
        List<CompanyRiskScore> companyRiskScoreList = Arrays.asList(
                new CompanyRiskScore("TCS", Arrays.asList(
                        new Dimensions("information_security", 80),
                        new Dimensions("resilience", 60),
                        new Dimensions("conduct", 70)
                ))
        );
        when(service.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllCompanyRiskScore")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].companyName", is("TCS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimensions[0].dimensionName", is("information_security")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimensions[0].dimensionValue", is(80.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimensions[1].dimensionName", is("resilience")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimensions[1].dimensionValue", is(60.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimensions[2].dimensionName", is("conduct")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dimensions[2].dimensionValue", is(70.0)));
        verify(service).getAllCompanyRiskScore();
    }

    @Test
    public void testGetCompanyRiskScoreAPI() throws Exception {
        CompanyRiskScore companyRiskScore =
                new CompanyRiskScore("TCS", Arrays.asList(
                        new Dimensions("information_security", 80),
                        new Dimensions("resilience", 60),
                        new Dimensions("conduct", 70)
                ));
        when(service.getCompanyRiskScore("TCS")).thenReturn(companyRiskScore);
        mockMvc.perform(MockMvcRequestBuilders.get("/getCompanyRiskScore/{companyName}", "TCS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName", is("TCS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[0].dimensionName", is("information_security")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[0].dimensionValue", is(80.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[1].dimensionName", is("resilience")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[1].dimensionValue", is(60.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[2].dimensionName", is("conduct")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[2].dimensionValue", is(70.0)));
        verify(service).getCompanyRiskScore("TCS");
    }

    @Test
    public void testAddCompanyRiskScore() throws Exception {
        CompanyRiskScore companyRiskScore =
                new CompanyRiskScore("TCS", Arrays.asList(
                        new Dimensions("information_security", 80),
                        new Dimensions("resilience", 60),
                        new Dimensions("conduct", 70)
                ));
        String requestBody = objectMapper.writeValueAsString(companyRiskScore);

        when(service.addCompanyRiskScore(companyRiskScore)).thenReturn(companyRiskScore);
        mockMvc.perform(MockMvcRequestBuilders.post("/addCompanyRiskScore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName", is("TCS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[0].dimensionName", is("information_security")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[0].dimensionValue", is(80.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[1].dimensionName", is("resilience")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[1].dimensionValue", is(60.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[2].dimensionName", is("conduct")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[2].dimensionValue", is(70.0)));
        verify(service).addCompanyRiskScore(any(CompanyRiskScore.class));
    }

    @Test
    public void testUpdateCompanyRiskScore() throws Exception {
        CompanyRiskScore companyRiskScore =
                new CompanyRiskScore("TCS", Arrays.asList(
                        new Dimensions("information_security", 80),
                        new Dimensions("resilience", 60),
                        new Dimensions("conduct", 50)
                ));
        String requestBody = objectMapper.writeValueAsString(companyRiskScore);

        when(service.updateCompanyRiskScore(companyRiskScore)).thenReturn(companyRiskScore);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateCompanyRiskScore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName", is("TCS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[0].dimensionName", is("information_security")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[0].dimensionValue", is(80.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[1].dimensionName", is("resilience")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[1].dimensionValue", is(60.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[2].dimensionName", is("conduct")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions[2].dimensionValue", is(50.0)));
        verify(service).updateCompanyRiskScore(any(CompanyRiskScore.class));
    }

    @Test
    public void testDeleteCompanyRiskScore() throws Exception {
        doNothing().when(service).deleteCompanyRiskScore("TCS");
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCompanyRiskScore/{companyName}","TCS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).deleteCompanyRiskScore("TCS");
    }


}