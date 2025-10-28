package com.HBA.dto;

import lombok.Data;

@Data
public class DTOSaledCar extends DTOBase {

	private DTOCustomer customer;
	
	private DTOGallerist gallerist;
	
	private DTOCar car;
	
}
