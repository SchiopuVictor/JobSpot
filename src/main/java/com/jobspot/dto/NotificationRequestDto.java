package com.jobspot.dto;

import com.jobspot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDto {

    private Long user_id;
    private String title;
    private String message;
}
