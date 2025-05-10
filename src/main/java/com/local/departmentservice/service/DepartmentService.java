package com.local.departmentservice.service;

import java.util.List;

import com.local.departmentservice.entity.Department;
import com.local.departmentservice.exceptions.DepartmentAlreadyExistsException;
import com.local.departmentservice.exceptions.DepartmentNotFoundException;

public interface DepartmentService {
	
	

	Department saveDepartment(Department department) throws DepartmentAlreadyExistsException, DepartmentNotFoundException;

	Department saveDepartment(Department department, long emp_id) throws  DepartmentAlreadyExistsException, DepartmentNotFoundException;

	List<Department> fetchDepartmentList();

	Department updateDepartment(Department department, Long departmentId);

	void deleteDepartmentById(Long departmentId);

	Department saveDepartmentFisrtTime(Department department);
}
