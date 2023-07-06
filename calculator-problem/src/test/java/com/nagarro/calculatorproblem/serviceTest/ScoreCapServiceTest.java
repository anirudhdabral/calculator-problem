package com.nagarro.calculatorproblem.serviceTest;

import com.nagarro.calculatorproblem.model.ScoreCap;
import com.nagarro.calculatorproblem.repo.ScoreCapRepository;
import com.nagarro.calculatorproblem.service.impl.ScoreCapServiceImpl;
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
public class ScoreCapServiceTest {
    @MockBean
    private ScoreCapRepository repository;
    @Autowired
    private ScoreCapServiceImpl service;

    private final List<ScoreCap> scoreCapList = Arrays.asList(
            new ScoreCap("one_very_high_risk", 40),
            new ScoreCap("two_very_high_risk", 30),
            new ScoreCap("one_high_risk", 60),
            new ScoreCap("two_high_risk", 50)
    );

    private final ScoreCap scoreCap = new ScoreCap("one_high_risk", 60);

    @Test
    public void testGetAllScoreCap() {
        when(repository.findAll()).thenReturn(scoreCapList);
        assertEquals(scoreCapList, service.getAllScoreCap());
    }

    @Test
    public void testGetScoreCap() {
        when(repository.findById("21-40")).thenReturn(Optional.of(scoreCap));
        assertEquals(scoreCap, service.getScoreCap("21-40"));
    }

    @Test
    public void testAddScoreCap() {
        when(repository.save(scoreCap)).thenReturn(scoreCap);
        assertEquals(scoreCap, service.addScoreCap(scoreCap));
    }

    @Test
    public void testUpdateScoreCap() {
        when(repository.save(scoreCap)).thenReturn(scoreCap);
        when(repository.findById("21-40")).thenReturn(Optional.of(scoreCap));
        assertEquals(scoreCap, service.addScoreCap(scoreCap));
    }

    @Test
    public void testDeleteScoreCap() {
        doNothing().when(repository).deleteById("21-40");
        repository.deleteById("21-40");
    }
}
