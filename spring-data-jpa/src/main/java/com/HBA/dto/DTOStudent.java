package com.HBA.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOStudent {

	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private List<DTOCourse> courses = new ArrayList<>();
}
