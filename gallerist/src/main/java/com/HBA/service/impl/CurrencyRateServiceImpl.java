package com.HBA.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.HBA.dto.CurrencyRateResponse;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.service.ICurrencyRateService;

@Service
public class CurrencyRateServiceImpl implements ICurrencyRateService {

	@Override
	public CurrencyRateResponse getCurrencyRate(String startDate, String endDate) {
		String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
		String series = "TP.DK.USD.A.YTL";
		String type = "json";
		
		String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type; //URL oluşturuldu
		
		//https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A.YTL&startDate=27-10-2025&endDate=27-10-2025&type=json
		
		HttpHeaders httpHeaders = new HttpHeaders(); //API key için headers verildi
		httpHeaders.set("key", "vIA8jVbt28");
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		try {
			RestTemplate restTemplate = new RestTemplate(); //API isteği için
			
			ResponseEntity<CurrencyRateResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRateResponse>() {
			});
			if (response.getStatusCode().is2xxSuccessful()) { //İstek başarılı ise
				return response.getBody();
			}
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.CURRENCY_RATES_IS_OCCURED));
		}
		
		return null;
	}

}
