package com.jobspot.dto;

import com.jobspot.entity.InternshipStatus;
import com.jobspot.entity.InternshipType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class InternshipRequestDto {
    private Long company_id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String location;
    private InternshipType type;
    private Instant duration;
    private String availablePositions;
    private BigDecimal salary;
    private Instant applicationDeadline;
    private LocalDate createdAt;
    private InternshipStatus status;
}
