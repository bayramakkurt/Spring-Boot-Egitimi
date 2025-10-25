package com.HBA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOEmployee {

	private Long id;
	
	private String name;
	
	private DTODepartment department;
}
