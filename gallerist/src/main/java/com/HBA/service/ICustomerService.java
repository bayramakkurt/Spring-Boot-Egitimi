package com.HBA.service;

import com.HBA.dto.DTOCustomer;
import com.HBA.dto.DTOCustomerIU;

public interface ICustomerService {

	public DTOCustomer saveCustomer(DTOCustomerIU dtoCustomerIU);
}
