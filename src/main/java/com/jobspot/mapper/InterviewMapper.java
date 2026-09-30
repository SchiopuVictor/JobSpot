package com.jobspot.mapper;

import com.jobspot.dto.InternshipResponseDto;
import com.jobspot.dto.InterviewRequestDto;
import com.jobspot.dto.InterviewResponseDto;
import com.jobspot.entity.Interview;

public class InterviewMapper {

    public static InterviewResponseDto toDto(Interview interview){
        return InterviewResponseDto.builder()
                .applicationName(interview.getApplication().getInternship().getTitle())
                .date(interview.getDate())
                .type(interview.getType())
                .time(interview.getTime())
                .meetingLink(interview.getMeetingLink())
                .location(interview.getLocation())
                .status(interview.getStatus())
                .build();
    }

    public static Interview toEntity(InterviewRequestDto request){
        return Interview.builder()
                .date(request.getDate())
                .type(request.getType())
                .meetingLink(request.getMeetingLink())
                .status(request.getStatus())
                .time(request.getTime())
                .location(request.getLocation())
                .build();
    }
}
