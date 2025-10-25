package com.HBA.services;

import com.HBA.dto.DTOCustomer;

public interface ICustomerService {
	
	public DTOCustomer findCustomerByID(Long id);

}
