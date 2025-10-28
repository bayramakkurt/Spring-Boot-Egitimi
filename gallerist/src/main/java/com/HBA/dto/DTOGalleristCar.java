package com.HBA.dto;

import lombok.Data;

@Data
public class DTOGalleristCar extends DTOBase {

	private DTOGallerist gallerist;
	
	private DTOCar car;
}
