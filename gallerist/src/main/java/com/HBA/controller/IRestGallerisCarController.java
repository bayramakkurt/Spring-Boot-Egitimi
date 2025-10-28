package com.HBA.controller;

import com.HBA.dto.DTOGalleristCar;
import com.HBA.dto.DTOGalleristCarIU;
import com.HBA.model.GalleristCar;

public interface IRestGallerisCarController {

	public RootEntity<DTOGalleristCar> saveGalleristCar(DTOGalleristCarIU dtoGalleristCarIU);
}
