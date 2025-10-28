package com.HBA.controller;

import com.HBA.dto.DTOCar;
import com.HBA.dto.DTOCarIU;

public interface IRestCarController {

	public RootEntity<DTOCar> saveCar(DTOCarIU dtoCarIU);
}
