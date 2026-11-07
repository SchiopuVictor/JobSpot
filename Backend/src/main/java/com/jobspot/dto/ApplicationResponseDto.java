package com.jobspot.dto;

import com.jobspot.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationResponseDto {
    private String studentName;
    private String internshipTitle;
    private String coverLetter;
    private String cvName;
    private ApplicationStatus status;
    private LocalDate appliedAt;

}
