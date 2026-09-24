package com.jobspot.mapper;

import com.jobspot.dto.NotificationRequestDto;
import com.jobspot.dto.NotificationResponseDto;
import com.jobspot.entity.Notification;

public class NotificationMapper {

    public static NotificationResponseDto toDto (Notification notification){
        return NotificationResponseDto.builder()
                .userName(notification.getUser().getFirstName()+" " +
                          notification.getUser().getLastName())
                .createdAt(notification.getCreatedAt())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .build();
    }

    public static Notification toEntity(NotificationRequestDto request){
        return Notification.builder()
                .title(request.getTitle())
                .message(request.getMessage())

                .build();
    }
}
