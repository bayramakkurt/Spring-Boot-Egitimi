package com.HBA.dto;

import java.math.BigDecimal;

import com.HBA.enums.CurrencyType;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DTOAccountIU {

	@NotEmpty
	private String accountNo;
	
	@NotEmpty
	private String iban;
	
	@NotEmpty
	private BigDecimal amount;
	
	@NotEmpty
	private CurrencyType currencyType;
}
