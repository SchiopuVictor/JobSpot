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

    private String userName;
    private String fileName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
}
