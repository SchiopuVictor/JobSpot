package com.jobspot.dto;

import lombok.Data;

@Data
public class EvaluationRequestDto {

    private Long studentId;
    private Long internshipId;
    private String technicalSkills;
    private String communication;
    private String teamwork;
    private String responsibility;
    private Integer overallRating;
    private String comment;

}
