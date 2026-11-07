package com.jobspot.mapper;

import com.jobspot.dto.TehRequestDto;
import com.jobspot.dto.TehResponseDto;
import com.jobspot.entity.Technology;

public class TehMapper {

    public static TehResponseDto toDto(Technology technology){
        return TehResponseDto.builder()
                .name(technology.getName())
                .build();
    }

    public static Technology toEntity(TehRequestDto request){
        return Technology.builder()
                .name(request.getName())
                .build();
    }
}
