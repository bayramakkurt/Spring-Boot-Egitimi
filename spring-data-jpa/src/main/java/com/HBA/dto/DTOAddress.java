package com.HBA.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class DTOAddress {

	
	private Long id;
	
	private String description;
	
	private DTOCustomer customer;
}
