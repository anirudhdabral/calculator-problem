package com.nagarro.calculatorproblem.controller;

import com.nagarro.calculatorproblem.model.ScoreCap;
import com.nagarro.calculatorproblem.service.impl.ScoreCapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ScoreCapController {
    @Autowired
    private ScoreCapServiceImpl service;

    @GetMapping("/getAllScoreCap")
    public List<ScoreCap> getAllScoreCap() {
        return service.getAllScoreCap();
    }

    @GetMapping("/getScoreCap/{condition}")
    public ScoreCap getScoreCap(@PathVariable String condition) {
        return service.getScoreCap(condition);
    }

    @PostMapping("/addScoreCap")
    public ScoreCap addScoreCap(@RequestBody ScoreCap scoreCap) {
        return service.addScoreCap(scoreCap);
    }

    @PutMapping("/updateScoreCap")
    public ScoreCap updateScoreCap(@RequestBody ScoreCap scoreCap) {
        return service.updateScoreCap(scoreCap);
    }

    @DeleteMapping("/deleteScoreCap/{condition}")
    public void deleteScoreCap(@PathVariable String condition) {
        service.deleteScoreCap(condition);
    }
}
