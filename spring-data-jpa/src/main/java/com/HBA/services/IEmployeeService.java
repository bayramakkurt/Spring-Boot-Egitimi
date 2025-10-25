package com.HBA.services;

import java.util.List;

import com.HBA.dto.DTOEmployee;

public interface IEmployeeService {

	public List<DTOEmployee> findAllEmployees();
}
