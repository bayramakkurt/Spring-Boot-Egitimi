package com.HBA.dto;

import java.util.Date;

import com.HBA.model.Account;
import com.HBA.model.Address;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DTOCustomerIU {

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String tckn;
	
	@NotNull
	private Date birthOfDate;
	
	@NotNull
	private Long addressId;
	
	@NotNull
	private Long accountId;
}
