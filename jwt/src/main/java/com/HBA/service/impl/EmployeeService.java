package com.HBA.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTODepartment;
import com.HBA.dto.DTOEmployee;
import com.HBA.model.Employee;
import com.HBA.repository.EmployeeRepository;
import com.HBA.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DTOEmployee findEmployeeByID(Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			DTOEmployee dtoEmployee = new DTOEmployee();
			BeanUtils.copyProperties(employee, dtoEmployee);
			DTODepartment dtoDepartment = new DTODepartment();
			BeanUtils.copyProperties(employee.getDepartment(), dtoDepartment);
			dtoEmployee.setDepartment(dtoDepartment);
			return dtoEmployee;
		}
		return null;
	}

}
