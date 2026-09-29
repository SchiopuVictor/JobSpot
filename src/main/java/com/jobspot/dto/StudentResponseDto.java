package com.jobspot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentResponseDto {
    private Long user_id;
    private String firstName;
    private String lastName;
    private String university;
    private String faculty;
    private Integer studyYear;
    private String city;
    private String description;

}
