package com.jobspot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CvResponseDto {

    private long student_id;
    private String fileName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
}
