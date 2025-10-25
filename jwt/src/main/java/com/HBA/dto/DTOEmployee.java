package com.HBA.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOEmployee {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private DTODepartment department;
}
