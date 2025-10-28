package com.HBA.dto;

import java.math.BigDecimal;

import com.HBA.enums.CurrencyType;

import lombok.Data;

@Data
public class DTOAccount extends DTOBase {

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
}
