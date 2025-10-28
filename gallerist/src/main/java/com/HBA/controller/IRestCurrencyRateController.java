package com.HBA.controller;

import com.HBA.dto.CurrencyRateResponse;

public interface IRestCurrencyRateController {

	public RootEntity<CurrencyRateResponse> getCurrencyRate(String startDate, String endDate);
}
