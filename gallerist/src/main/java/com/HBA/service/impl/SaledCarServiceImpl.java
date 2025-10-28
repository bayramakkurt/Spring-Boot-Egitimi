package com.HBA.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.HBA.dto.CurrencyRateResponse;
import com.HBA.dto.DTOCar;
import com.HBA.dto.DTOCustomer;
import com.HBA.dto.DTOGallerist;
import com.HBA.dto.DTOSaledCar;
import com.HBA.dto.DTOSaledCarIU;
import com.HBA.enums.CarStatusType;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.model.Car;
import com.HBA.model.Customer;
import com.HBA.model.SaledCar;
import com.HBA.repository.CarRepository;
import com.HBA.repository.CustomerRepository;
import com.HBA.repository.GalleristRepository;
import com.HBA.repository.SaledCarRepository;
import com.HBA.service.ICurrencyRateService;
import com.HBA.service.ISaledCarService;
import com.HBA.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private CarRepository carRepository; 
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private ICurrencyRateService currencyRateService;

	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRateResponse currencyRateResponse = currencyRateService.getCurrencyRate(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRateResponse.getItems().get(0).getUSD()); //Güncel kuru aldık
		
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP); //Müşteri parasını dolara çevirdik.
		return customerUSDAmount;
	}
	
	private BigDecimal remainingCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
	
		CurrencyRateResponse currencyRateResponse = currencyRateService.getCurrencyRate(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRateResponse.getItems().get(0).getUSD());
		return remainingCustomerUSDAmount.multiply(usd);
	}
	
	private boolean checkCarStatus(Long carId) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		if (optionalCar.isPresent() && optionalCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	
	}
	
	private boolean checkAmount(DTOSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optionalCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId()); //Müşterinin varlığı veritabanından teyitlemek için
		if (optionalCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCustomerId().toString(), MessageType.NO_RECORD_EXIST));
		}
		Optional<Car> optionalCar = carRepository.findById(dtoSaledCarIU.getCarId()); //Arabanın varlığını veritabanından teyitlemek için
		if (optionalCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optionalCustomer.get());
		
		if (customerUSDAmount.compareTo(optionalCar.get().getPrice()) == 0 | customerUSDAmount.compareTo(optionalCar.get().getPrice()) > 0 ) {
			return true;
		}
		return false;
	
	}
	
	private SaledCar createSaledCar(DTOSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null)); //Bulabilirse Customer DB den dön bulamazsa NULL dön.
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}
	

	@Override
	public DTOSaledCar buyCar(DTOSaledCarIU dtoSaledCarIU) {
		if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCarId().toString(), MessageType.CAR_STATUS_IS_ALREADY_SALED));
		}
		
		
		if (!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage("", MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU)); //Arabanın satış işlemi DB üzerinde gerçekleşti
		
		Car car = savedSaledCar.getCar(); //Arabanın satış durumu satıldı olarak güncellendi.
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		return toDTO(savedSaledCar);
	}
	
	
	private DTOSaledCar toDTO(SaledCar saledCar) {
		DTOSaledCar dtoSaledCar = new DTOSaledCar();
		DTOCustomer dtoCustomer = new DTOCustomer();
		DTOGallerist dtoGallerist = new DTOGallerist();
		DTOCar dtoCar = new DTOCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		
		dtoSaledCar.setCar(dtoCar);
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		return dtoSaledCar;
	}

}
