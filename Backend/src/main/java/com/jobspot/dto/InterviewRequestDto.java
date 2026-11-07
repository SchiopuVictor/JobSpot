package com.jobspot.dto;

import com.jobspot.entity.Status;
import com.jobspot.entity.Type;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class InterviewRequestDto {
    private Long applicationId;
    private LocalDate date;
    private LocalTime time;
    private Type type;
    private String meetingLink;
    private String location;
    private Status status;
}
