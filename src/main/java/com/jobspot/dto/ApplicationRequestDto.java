package com.jobspot.dto;


import com.jobspot.entity.ApplicationStatus;
import lombok.Data;

@Data
public class ApplicationRequestDto {
    private Long student_id;
    private Long internship_id;
    private String coverLetter;
    private Long cv_id;
    private ApplicationStatus status;
}
