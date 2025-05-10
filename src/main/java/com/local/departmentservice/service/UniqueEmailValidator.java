package com.local.departmentservice.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueEmailValidation.class })
public @interface UniqueEmailValidator {

	public String message() default "Invalid EmpEmail Address";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
