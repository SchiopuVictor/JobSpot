package com.jobspot.mapper;

import com.jobspot.dto.CvRequestDto;
import com.jobspot.dto.CvResponseDto;
import com.jobspot.entity.Cv;

public class CvMapper {

    public static CvResponseDto toDto(Cv cv){
        return CvResponseDto.builder()
//                .userName(cv.getStudent().getFirstName()+ " "+cv.getStudent().getLastName())
                .createdAt(cv.getUploadedAt())
                .fileName(cv.getFileName())
                .build();
    }

    public static Cv toEntity(CvRequestDto request){
            return Cv.builder()
                    .fileName(request.getFileName())
                    .build();

    }
}
