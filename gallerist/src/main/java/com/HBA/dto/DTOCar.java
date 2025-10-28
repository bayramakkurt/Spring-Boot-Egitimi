package com.HBA.dto;

import java.math.BigDecimal;

import com.HBA.enums.CarStatusType;
import com.HBA.enums.CurrencyType;

import lombok.Data;

@Data
public class DTOCar extends DTOBase {

	private String plaka;
	
	private String brand;
	
	private String model;
	
	private Integer productionYear;
	
	private BigDecimal price;
	
	private CurrencyType currencyType;
	
	private BigDecimal damagePrice;
	
	private CarStatusType carStatusType;
}
