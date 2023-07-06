package com.nagarro.calculatorproblem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_status")
public class JobStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private int id;

    @Column(name = "output_status")
    private String outputStatus="FAILED";

    @Column(name = "job_date_time")
    private LocalDateTime jobDateTime;

    @Column(name = "output_error")
    private String jobError="No error";


}
