package com.jobspot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobspot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationResponseDto {

    private String userName;
    private String title;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;


}
