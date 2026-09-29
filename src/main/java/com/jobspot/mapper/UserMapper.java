package com.jobspot.mapper;

import com.jobspot.dto.UserRequestDto;
import com.jobspot.dto.UserResponseDto;
import com.jobspot.entity.User;

public class UserMapper {
    public static UserResponseDto toDto(User request){
        return UserResponseDto.builder()
                .email(request.getEmail())
                .role(request.getRole())
                .createdAt(request.getCreatedAt())
                .active(request.getActive())
                .build();
    }

    public static User toEntity(UserRequestDto request){
        return User.builder()
                .email(request.getEmail())
                .active(request.getActive())
                .role(request.getRole())
                .password(request.getPassword())
                .build();

    }

}
