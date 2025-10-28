package com.HBA.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOCar;
import com.HBA.dto.DTOGallerist;
import com.HBA.dto.DTOGalleristCar;
import com.HBA.dto.DTOGalleristCarIU;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.model.Car;
import com.HBA.model.Gallerist;
import com.HBA.model.GalleristCar;
import com.HBA.repository.CarRepository;
import com.HBA.repository.GalleristCarRepository;
import com.HBA.repository.GalleristRepository;
import com.HBA.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl  implements IGalleristCarService{
	
	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	private GalleristCar createGalleristCar(DTOGalleristCarIU dtoGalleristCarIU) {
		Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if (optionalGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST));
		}
		Optional<Car> optionalCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if (optionalCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}		
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setCar(optionalCar.get());
		galleristCar.setGallerist(optionalGallerist.get());
	
		return galleristCar;
	}

	@Override
	public DTOGalleristCar saveGalleristCar(DTOGalleristCarIU dtoGalleristCarIU) {
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		DTOGalleristCar dtoGalleristCar = new DTOGalleristCar();
		DTOGallerist dtoGallerist = new DTOGallerist();
		DTOCar dtoCar = new DTOCar();
		DTOAddress dtoAddress = new DTOAddress();
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		
		dtoGallerist.setAddress(dtoAddress); 
		dtoGalleristCar.setCar(dtoCar);
		dtoGalleristCar.setGallerist(dtoGallerist);
		return dtoGalleristCar;
	}

}
