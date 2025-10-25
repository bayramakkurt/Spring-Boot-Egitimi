package com.HBA.service;

import com.HBA.dto.DTOEmployee;
import com.HBA.model.Employee;

public interface IEmployeeService {

	public DTOEmployee findEmployeeByID(Long id);
}
