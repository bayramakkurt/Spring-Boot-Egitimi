package com.HBA.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DTOGalleristIU {

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private Long addressId;
}
