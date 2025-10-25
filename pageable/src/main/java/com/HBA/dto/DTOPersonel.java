package com.HBA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPersonel {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private DTODepartment department;
}
