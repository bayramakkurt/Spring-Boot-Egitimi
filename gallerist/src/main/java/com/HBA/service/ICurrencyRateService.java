package com.HBA.service;

import com.HBA.dto.CurrencyRateResponse;

public interface ICurrencyRateService {

	public CurrencyRateResponse getCurrencyRate(String startDate, String endDate);
}
