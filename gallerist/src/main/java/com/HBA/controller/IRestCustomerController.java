package com.HBA.controller;

import com.HBA.dto.DTOCustomer;
import com.HBA.dto.DTOCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DTOCustomer> saveCustomer(DTOCustomerIU dtoCustomerIU);
}
