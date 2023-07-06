package com.nagarro.calculatorproblem.controllerTest;

import com.nagarro.calculatorproblem.controller.JobStatusController;
import com.nagarro.calculatorproblem.model.JobStatus;
import com.nagarro.calculatorproblem.service.impl.JobStatusServiceImpl;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JobStatusController.class)
public class JobStatusControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JobStatusServiceImpl service;

    @Test
    public void testGetAllJobStatusAPI() throws Exception {
        List<JobStatus> jobStatusList = Arrays.asList(
                new JobStatus(1, "SUCCESS", LocalDateTime.now(), "No error"),
                new JobStatus(2, "FAILED", LocalDateTime.now(), "Cannot calculate for company: XYZ")
        );
        when(service.getAllJobStatus()).thenReturn(jobStatusList);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllJobStatus")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].outputStatus", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].jobError", is("No error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].outputStatus", is("FAILED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].jobError", is("Cannot calculate for company: XYZ")))
        ;
        verify(service).getAllJobStatus();
    }
}
