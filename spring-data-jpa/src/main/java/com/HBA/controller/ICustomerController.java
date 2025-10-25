package com.HBA.controller;

import com.HBA.dto.DTOCustomer;

public interface ICustomerController {
	
	public DTOCustomer findCustomerByID(Long id);

}
