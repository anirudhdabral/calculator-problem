package com.nagarro.calculatorproblem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculatorproblem.controller.ScoreCapController;
import com.nagarro.calculatorproblem.model.ScoreCap;
import com.nagarro.calculatorproblem.service.impl.ScoreCapServiceImpl;
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
@WebMvcTest(ScoreCapController.class)
public class ScoreCapControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScoreCapServiceImpl service;

    @Test
    public void testGetAllScoreCapAPI() throws Exception {
        List<ScoreCap> scoreCapList = Arrays.asList(
                new ScoreCap("one_very_high_risk", 40),
                new ScoreCap("two_very_high_risk", 30)
        );
        when(service.getAllScoreCap()).thenReturn(scoreCapList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllScoreCap")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].condition", is("one_very_high_risk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].totalRiskCappedScore", is(40)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].condition", is("two_very_high_risk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].totalRiskCappedScore", is(30)));
        verify(service).getAllScoreCap();
    }

    @Test
    public void testGetScoreCapAPI() throws Exception {
        ScoreCap scoreCap = new ScoreCap("two_very_high_risk", 30);
        when(service.getScoreCap("two_very_high_risk")).thenReturn(scoreCap);
        mockMvc.perform(MockMvcRequestBuilders.get("/getScoreCap/{condition}", "two_very_high_risk")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", is("two_very_high_risk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRiskCappedScore", is(30)));
        verify(service).getScoreCap("two_very_high_risk");
    }

    @Test
    public void testAddScoreCap() throws Exception {
        ScoreCap scoreCap =
                new ScoreCap("two_very_high_risk",
                        30);
        String requestBody = objectMapper.writeValueAsString(scoreCap);
        when(service.addScoreCap(scoreCap)).thenReturn(scoreCap);
        mockMvc.perform(MockMvcRequestBuilders.post("/addScoreCap")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", is("two_very_high_risk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRiskCappedScore", is(30)));
        verify(service).addScoreCap(any(ScoreCap.class));
    }

    @Test
    public void testUpdateScoreCap() throws Exception {
        ScoreCap scoreCap = new ScoreCap("two_very_high_risk",
                30);
        String requestBody = objectMapper.writeValueAsString(scoreCap);
        when(service.updateScoreCap(scoreCap)).thenReturn(scoreCap);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateScoreCap")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", is("two_very_high_risk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRiskCappedScore", is(30)));
        ;
        verify(service).updateScoreCap(any(ScoreCap.class));
    }

    @Test
    public void testDeleteScoreCap() throws Exception {
        doNothing().when(service).deleteScoreCap("two_very_high_risk");
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteScoreCap/{condition}", "two_very_high_risk")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).deleteScoreCap("two_very_high_risk");
    }
}


