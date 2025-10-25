package com.HBA.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTODepartment;
import com.HBA.dto.DTOEmployee;
import com.HBA.entities.Employee;
import com.HBA.repository.EmployeeRepository;
import com.HBA.services.IEmployeeService;

@Service
public class EmployeeServiceImpl  implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<DTOEmployee> findAllEmployees() {
		List<DTOEmployee> dtoEmployeeList = new ArrayList<>();
		List<Employee> employeeList = employeeRepository.findAll();
		if (employeeList != null && !employeeList.isEmpty()) {
			for (Employee employee : employeeList) {
				DTOEmployee dtoEmployee = new DTOEmployee();
				BeanUtils.copyProperties(employee, dtoEmployee);
				
				dtoEmployee.setDepartment(new DTODepartment(employee.getDepartment().getId(), employee.getDepartment().getDepartmentName()));
				
				dtoEmployeeList.add(dtoEmployee);
			}
		}
		
		return dtoEmployeeList;
	}
	

}
