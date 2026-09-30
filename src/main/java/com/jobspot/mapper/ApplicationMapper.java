package com.jobspot.mapper;

import com.jobspot.dto.ApplicationRequestDto;
import com.jobspot.dto.ApplicationResponseDto;
import com.jobspot.entity.Application;

public class ApplicationMapper {

    public static ApplicationResponseDto toDto(Application application){
        return ApplicationResponseDto.builder()
                .studentName(application.getStudent().getFirstName()+" "+application.getStudent().getLastName())
                .coverLetter(application.getCoverLetter())
                .internshipTitle(application.getInternship().getTitle())
                .cvName(application.getCv().getFileName())
                .appliedAt(application.getAppliedAt())
                .status(application.getStatus())
                .build();
    }

    public static Application toEntity(ApplicationRequestDto request){
        return Application.builder()
                .coverLetter(request.getCoverLetter())
                .status(request.getStatus())
                .build();
    }
}
