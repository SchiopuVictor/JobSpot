package com.jobspot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobspot.anotations.ValidateRole;
import com.jobspot.entity.Role;
import jakarta.validation.constraints.Email;
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
    @Email(message = "this bust be an email!!")
    private String email;
    @ValidateRole(message = "this field must be completed!!")
    private Role role;
    private String phone;
    private String password;
    private Boolean active;
}
