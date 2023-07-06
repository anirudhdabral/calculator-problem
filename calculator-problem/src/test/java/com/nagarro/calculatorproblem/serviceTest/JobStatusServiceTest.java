package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.JobStatus;
import com.nagarro.calculatorproblem.repo.JobStatusRepository;
import com.nagarro.calculatorproblem.service.impl.JobStatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JobStatusServiceTest {
    @MockBean
    private JobStatusRepository jobStatusRepository;
    @Autowired
    private JobStatusServiceImpl jobStatusService;

    private final List<JobStatus> jobStatusList = Arrays.asList(
            new JobStatus(1, "SUCCESS", LocalDateTime.now(), "No error"),
            new JobStatus(2, "FAILED", LocalDateTime.now(), "Cannot calculate for company: XYZ")
    );

    @Test
    public void testGetAllJobStatus() {
        when(jobStatusRepository.findAll()).thenReturn(jobStatusList);
        assertEquals(jobStatusList, jobStatusService.getAllJobStatus());
    }
}
