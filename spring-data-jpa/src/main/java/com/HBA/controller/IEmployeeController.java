package com.HBA.controller;

import java.util.List;

import com.HBA.dto.DTOEmployee;

public interface IEmployeeController {

	public List<DTOEmployee> findAllEmployees();
}
