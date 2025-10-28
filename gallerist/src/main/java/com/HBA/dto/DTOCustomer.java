package com.HBA.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DTOCustomer extends DTOBase {

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date birthOfDate;
	
	private DTOAddress address;
	
	private DTOAccount account;
	
}
