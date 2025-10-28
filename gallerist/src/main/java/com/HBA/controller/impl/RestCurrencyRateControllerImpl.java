package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestCurrencyRateController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.CurrencyRateResponse;
import com.HBA.service.ICurrencyRateService;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRateControllerImpl extends RestBaseController implements IRestCurrencyRateController {
	
	@Autowired
	private ICurrencyRateService currencyRateService;

	@GetMapping("/currency-rate")
	@Override
	public RootEntity<CurrencyRateResponse> getCurrencyRate(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		return RootEntity.ok(currencyRateService.getCurrencyRate(startDate, endDate));
	}

	
}
