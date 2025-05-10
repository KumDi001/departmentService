package com.local.departmentservice.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.local.departmentservice.entity.Department;
import com.local.departmentservice.exceptions.DepartmentAlreadyExistsException;
import com.local.departmentservice.exceptions.DepartmentNotFoundException;
import com.local.departmentservice.repository.DepartmentRepository;
import com.local.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository; // Injects the DepartmentRepository dependency.

	@Override
	@Transactional
	public Department saveDepartment(Department department, long emp_id)
			throws DepartmentAlreadyExistsException, DepartmentNotFoundException {
		// Saves and returns the department entity.
		// List<Employee> empList = department.getEmployee();
		department.setDepartmentName(null);
		return departmentRepository.save(department);
	}

	@Override
	public Department saveDepartmentFisrtTime(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		// Retrieves and returns a list of all department entities.
		return (List<Department>) departmentRepository.findAll();
	}

	@Override
	public Department updateDepartment(Department department, Long departmentId) {
		// Finds the existing department by ID.
		Department depDB = departmentRepository.findById(departmentId).get();

		// Updates fields if they are not null or empty.
		if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
			depDB.setDepartmentName(department.getDepartmentName());
		}
		if (Objects.nonNull(department.getDepartmentAddress())
				&& !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			depDB.setDepartmentAddress(department.getDepartmentAddress());
		}
		// Saves and returns the updated department entity.
		return departmentRepository.save(depDB);
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		// Deletes the department entity by its ID.
		departmentRepository.deleteById(departmentId);
	}

	@Override
	public Department saveDepartment(Department department)
			throws DepartmentAlreadyExistsException, DepartmentNotFoundException {
		// TODO Auto-generated method stub
		return departmentRepository.save(department);
	}

}
