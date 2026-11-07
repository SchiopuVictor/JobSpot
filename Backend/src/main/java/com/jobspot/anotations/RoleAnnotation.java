package com.jobspot.anotations;

import com.jobspot.entity.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class RoleAnnotation implements ConstraintValidator<ValidateRole, Role> {
    public static final List<Role> ROLES= List.of(
            Role.Admin,
            Role.Company,
            Role.Student
    );

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        return role == null ||ROLES.contains(role);
    }

}
