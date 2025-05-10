package com.local.departmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.local.departmentservice.repository.DepartmentRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmailValidator, String> {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value == null) {
			return true;
		}

		// Check if email already exists
		var existingEmployee = departmentRepository.findByDepartmentEmailAddress(value);
		return existingEmployee == null; // true means it's unique
	}

}
