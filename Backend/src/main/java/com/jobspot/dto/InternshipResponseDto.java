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
    private long id;
    private String companyName;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String location;
    private InternshipType type;
    private String duration;
    private String working_schedule;
    private BigDecimal salary;
    private LocalDate applicationDeadline;
    private String imgUrl;
    private LocalDate createdAt;
    private InternshipStatus status;
}
