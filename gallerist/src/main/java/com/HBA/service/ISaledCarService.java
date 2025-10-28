package com.HBA.service;

import com.HBA.dto.DTOSaledCar;
import com.HBA.dto.DTOSaledCarIU;

public interface ISaledCarService {

	public DTOSaledCar buyCar(DTOSaledCarIU dtoSaledCarIU);
}
