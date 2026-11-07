package com.jobspot.dto;

import com.jobspot.entity.InternshipStatus;
import com.jobspot.entity.InternshipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternshipResponseDto {

    private String companyName;
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
