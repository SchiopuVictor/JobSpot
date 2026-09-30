package com.jobspot.mapper;

import com.jobspot.dto.InternshipRequestDto;
import com.jobspot.dto.InternshipResponseDto;
import com.jobspot.entity.Internship;

public class InternshipMapper {

    public static InternshipResponseDto toDto(Internship internship){
        return InternshipResponseDto.builder()
                .companyName(internship.getCompany().getCompanyName())
                .availablePositions(internship.getAvailablePositions())
                .applicationDeadline(internship.getApplicationDeadline())
                .createdAt(internship.getCreatedAt())
                .description(internship.getDescription())
                .title(internship.getTitle())
                .duration(internship.getDuration())
                .location(internship.getLocation())
                .requirements(internship.getRequirements())
                .responsibilities(internship.getResponsibilities())
                .salary(internship.getSalary())
                .status(internship.getStatus())
                .type(internship.getType())
                .build();
    }

    public static Internship toEntity(InternshipRequestDto request){
        return Internship.builder()
                .applicationDeadline(request.getApplicationDeadline())
                .status(request.getStatus())
                .availablePositions(request.getAvailablePositions())
                .description(request.getDescription())
                .duration(request.getDuration())
                .location(request.getLocation())
                .requirements(request.getRequirements())
                .responsibilities(request.getResponsibilities())
                .title(request.getTitle())
                .salary(request.getSalary())
                .type(request.getType())
                .build();
    }
}
