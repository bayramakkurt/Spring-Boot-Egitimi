package com.HBA.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOCar;
import com.HBA.dto.DTOCarIU;
import com.HBA.model.Car;
import com.HBA.repository.CarRepository;
import com.HBA.service.ICarService;

@Service
public class CarServiceImpl implements ICarService{
	
	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DTOCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoCarIU, car);
		return car;
	}
	

	@Override
	public DTOCar saveCar(DTOCarIU dtoCarIU) {
		Car savedCar = carRepository.save(createCar(dtoCarIU));
		DTOCar dtoCar = new DTOCar();
		
		BeanUtils.copyProperties(savedCar, dtoCar);
		return dtoCar;
	}

}
