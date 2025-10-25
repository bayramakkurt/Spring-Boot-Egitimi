package com.HBA.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IEmployeeController;
import com.HBA.dto.DTOEmployee;
import com.HBA.services.IEmployeeService;

@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeControllerImpl implements IEmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;

	@GetMapping(path = "/list")
	@Override
	public List<DTOEmployee> findAllEmployees() {
		return employeeService.findAllEmployees();
	}

}
