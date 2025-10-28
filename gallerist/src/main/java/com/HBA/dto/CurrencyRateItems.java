package com.HBA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurrencyRateItems {

	@JsonProperty("Tarih") //Burada API isteğinde dönen JSON değişkenlerini istediğimiz değişken adlarına dönüştürüyoruz.
	private String date;
	
	@JsonProperty("TP_DK_USD_A_YTL")
	private String USD;
}
