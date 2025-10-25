package com.HBA.service;

import com.HBA.dto.DTOEmployee;

public interface IEmployeeService {

	public DTOEmployee findEmployeeByID(Long id);
}
