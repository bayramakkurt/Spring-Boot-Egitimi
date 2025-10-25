package com.HBA.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTODepartment;
import com.HBA.dto.DTOEmployee;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.model.Department;
import com.HBA.model.Employee;
import com.HBA.repository.EmployeeRepository;
import com.HBA.service.IEmployeeService;

@Service
public class EmployeeServiceImpl  implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public DTOEmployee findEmployeeByID(Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if (optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
		Employee dbEmployee = optional.get();
		Department dbDepartment = optional.get().getDepartment();
		DTOEmployee dtoEmployee = new DTOEmployee();
		DTODepartment dtoDepartment = new DTODepartment();
		BeanUtils.copyProperties(dbEmployee, dtoEmployee);
		BeanUtils.copyProperties(dbDepartment, dtoDepartment);
		
		dtoEmployee.setDepartment(dtoDepartment);
		
		return dtoEmployee;
	}

}
