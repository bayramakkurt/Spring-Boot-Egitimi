package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.RestEmployeeController;
import com.HBA.dto.DTOEmployee;
import com.HBA.model.RootEntity;
import com.HBA.service.IEmployeeService;

@RestController
@RequestMapping("rest/api/employee")
public class RestEmployeeControllerImpl extends RestBaseController implements RestEmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping(path = "/list/{id}")
	@Override
	public RootEntity<DTOEmployee> findEmployeeByID(@PathVariable(name = "id")  Long id) {
		return ok(employeeService.findEmployeeByID(id));
	}

}
