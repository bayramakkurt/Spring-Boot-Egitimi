package com.HBA.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyRateResponse {

	private Integer totalCount;
	
	private List<CurrencyRateItems> items; 
}
