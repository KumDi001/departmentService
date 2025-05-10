package com.local.departmentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.local.departmentservice.entity.Department;
import com.local.departmentservice.exceptions.DepartmentAlreadyExistsException;
import com.local.departmentservice.exceptions.DepartmentNotFoundException;
import com.local.departmentservice.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService; // Injects the DepartmentService dependency.

	@PostMapping("/addDepartments")
	public Department saveDepartmentWithEmp(@Validated @RequestBody Department department)
			throws DepartmentAlreadyExistsException, DepartmentAlreadyExistsException, DepartmentNotFoundException {

		return departmentService.saveDepartment(department, 1);
	}

	@PostMapping("/addDepartment")
	public Department saveDepartment(@Validated @RequestBody Department department)
			throws DepartmentAlreadyExistsException, DepartmentAlreadyExistsException, DepartmentNotFoundException {

		return departmentService.saveDepartmentFisrtTime(department);
	}

	@GetMapping("/getDepartments")
	public List<Department> fetchDepartmentList() throws DepartmentNotFoundException {
		return departmentService.fetchDepartmentList();
	}

	@PutMapping("/departments/{id}")
	public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
		return departmentService.updateDepartment(department, departmentId);
	}

	@DeleteMapping("/departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Deleted Successfully";
	}

}
