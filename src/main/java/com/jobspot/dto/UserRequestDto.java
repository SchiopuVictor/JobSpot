package com.jobspot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobspot.anotations.ValidateRole;
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
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    @ValidateRole(message = "this field must be completed!!")
    private Role role;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private String phone;
    private String password;
    private Boolean active;
}
