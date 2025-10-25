package com.HBA.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  //Parametresiz constructor
@AllArgsConstructor //Parametreli constructor
public class Employee {
	
	private String id;
	private String firstName;
	private String lastName;
}
