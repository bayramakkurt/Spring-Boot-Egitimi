package com.HBA.controller.impl;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestEmployeeController;
import com.HBA.dto.DTOEmployee;
import com.HBA.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class RestEmployeeController  implements IRestEmployeeController{

	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping(path = "/{id}")
	@Override
	public DTOEmployee findEmployeeByID(@PathVariable(value = "id")  Long id) {
		return employeeService.findEmployeeByID(id);
	}

}
