package com.nagarro.calculatorproblem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class CalculatorProblemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorProblemApplication.class, args);
        String uri = "http://localhost:5055/resetDatabase";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(uri, String.class);
    }

}
