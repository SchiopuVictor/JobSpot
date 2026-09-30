package com.jobspot.dto;

import com.jobspot.entity.Application;
import com.jobspot.entity.Status;
import com.jobspot.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewResponseDto {
    private String applicationName;
    private LocalDate date;
    private LocalTime time;
    private Type type;
    private String meetingLink;
    private String location;
    private Status status;
}
