package com.local.departmentservice.entity;

import com.local.departmentservice.service.UniqueEmailValidator;
import com.local.departmentservice.service.UniqueValidatorGroup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id // Specifies the primary key of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key value.
	private Long departmentId; // Unique identifier for the department.
	@NotNull
	private String departmentName; // Name of the department.
	private String departmentAddress; // Address of the department.
	@jakarta.validation.constraints.Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@UniqueEmailValidator(message = "Email must be valid", groups = { UniqueValidatorGroup.class })
	private String departmentEmailAddress; // EmailAddress of the department.

}
