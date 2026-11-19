package com.jobspot.dto;


import com.jobspot.entity.ApplicationStatus;
import lombok.Data;

@Data
public class ApplicationRequestDto {
    private Long internship_id;
    private String coverLetter;
    private Long cv_id;
}
