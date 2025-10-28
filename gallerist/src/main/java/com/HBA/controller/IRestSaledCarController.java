package com.HBA.controller;

import com.HBA.dto.DTOSaledCar;
import com.HBA.dto.DTOSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DTOSaledCar> buyCar(DTOSaledCarIU dtoSaledCarIU);
}
