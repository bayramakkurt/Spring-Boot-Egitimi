package com.HBA.controller;

import com.HBA.dto.DTOEmployee;

public interface IRestEmployeeController {

	public DTOEmployee findEmployeeByID(Long id);
}
