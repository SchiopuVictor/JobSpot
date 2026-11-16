package com.jobspot.dto;

import com.jobspot.entity.InternshipStatus;
import com.jobspot.entity.InternshipType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class InternshipRequestDto {
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String location;
    private InternshipType type;
    private String duration;
    private String working_schedule;
    private String imgUrl;
    private BigDecimal salary;
    private LocalDate applicationDeadline;
    private InternshipStatus status;
}
