package com.jobspot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EvaluationResponseDto {

    private String studentName;
    private String internshipName;
    private String technicalSkills;
    private String communication;
    private String teamwork;
    private String responsibility;
    private Integer overallRating;
    private String comment;
    private LocalDate createdAt;
}
