package com.HBA.controller;

import com.HBA.dto.DTOEmployee;
import com.HBA.model.RootEntity;

public interface RestEmployeeController {

	public RootEntity<DTOEmployee> findEmployeeByID(Long id);
}
