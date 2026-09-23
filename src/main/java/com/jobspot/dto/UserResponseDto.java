package com.jobspot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobspot.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private Boolean active;

}
