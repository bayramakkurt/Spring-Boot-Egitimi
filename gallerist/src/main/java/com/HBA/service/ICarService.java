package com.HBA.service;

import com.HBA.dto.DTOCar;
import com.HBA.dto.DTOCarIU;

public interface ICarService {

	public DTOCar saveCar(DTOCarIU dtoCarIU);
}
