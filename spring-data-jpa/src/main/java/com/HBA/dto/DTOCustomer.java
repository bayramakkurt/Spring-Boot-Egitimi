package com.HBA.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL) //JSON çıktısında NULL değerler gözükmez.
public class DTOCustomer {
	
	private Long id;
	
	private String name;
	
	private DTOAddress address;

}
