package com.jobspot.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {RoleAnnotation.class})
public @interface ValidateRole {
    String message() default "this field is important!!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
