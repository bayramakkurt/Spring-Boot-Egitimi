package com.HBA.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DTOSaledCarIU {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Long galleristId;
	
	@NotNull
	private Long carId;
}
